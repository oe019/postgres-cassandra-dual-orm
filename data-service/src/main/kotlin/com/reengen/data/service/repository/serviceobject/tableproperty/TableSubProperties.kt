package com.reengen.data.service.repository.serviceobject.tableproperty

import com.reengen.data.service.repository.abstraction.ICompactionStrategy
import org.hibernate.engine.jdbc.Size
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties
import kotlin.reflect.jvm.isAccessible

class TableSubProperties {
    /*
   /*
    The compaction property defines the compaction strategy class for this table. Choose the compaction strategy that best fits your data and environment.
     */
    class Compaction {
        private var _STCS = TableSubProperties.SizeTieredCompactionStrategy()

        constructor(stcs: TableSubProperties.SizeTieredCompactionStrategy) {
            this._STCS = stcs
        }
    }
 */
        class SizeTieredCompactionStrategy(
            /*
            Definition: Size-tiered compaction merges sets of SSTables that are approximately the same size. Casssandra compares each SSTable size to the average of all SSTable sizes on the node. It merges SSTAbles whose sizes in KB are within [average-size × bucket_low] and [average-size × bucket_high].
             */
            var bucket_high: String = "1.5",
            /*
           Definition: See above
             */
            var bucket_row: String = "0.5",
            /*
           Definition: true enables background compaction. See Enabling and disabling background compaction.
             */
            var enabled: Boolean = true,
            /*
            Definition: Activates advanced logging for the entire cluster.
             */
            var log_all: Boolean = false,
            /*
            Definition: The maximum number of SSTables to allow in a minor compaction.
             */
            var max_threshold: String = "32",
            /*
            Definition: The minimum number of SSTables to trigger a minor compaction.
             */
            var min_threshold: String = "4",
            /*
            Definition: STCS groups SSTables into buckets. The bucketing process groups SSTables that differ in size by less than 50%. This bucketing process is too fine grained for small SSTables. If your SSTables are small, use min_sstable_size to define a size threshold (in bytes) below which all SSTables belong to one unique bucket.
             */
            var min_sstable_size: String = "50",
            /*
            Definition: In Apache Cassandra™ 3.0 and later: true allows purging tombstones only from repaired SSTables. The purpose is to prevent data from resurrecting if repair is not run within gc_grace_seconds. If you do not run repair for a long time, Cassandra keeps all tombstones — this may cause problems.
             */
            var only_purge_repaired_tombstones: Boolean = false,
            /*
            Description: The minimum number of seconds after an SSTable is created before Cassandra considers the SSTable for tombstone compaction. Cassandra performs tombstone compaction on an SSTable if the table exceeds the tombstone_threshold ratio.
             */
            var tombstone_compaction_interval: String = "86400",
            /*
                 The ratio of garbage-collectable tombstones to all contained columns. If the ratio exceeds this limit, Cassandra starts compaction on that table alone, to purge the tombstones.
             */
            var tombstone_threshold: String = "0.2",
            /*
            True allows Cassandra to run tombstone compaction without pre-checking which tables are eligible for this operation. Even without this pre-check, Cassandra checks an SSTable to make sure it is safe to drop tombstones.
             */
            var unchecked_tombstone_compaction: Boolean = false) : ICompactionStrategy {

        private val _keyValues :StringBuilder = StringBuilder().append("'class':").append("'${this::class.simpleName}',")

          fun ToString(): String {
            this.gatherPropertyKeyValues()
            return _keyValues.toString()
        }
         fun gatherPropertyKeyValues(){

             for(prop in SizeTieredCompactionStrategy::class.memberProperties){
                prop.isAccessible = true
                 if(prop.name.startsWith("_"))
                     continue
                 _keyValues.append("'${prop.name}' : '${prop.get(this)}',")
             }
             _keyValues.deleteCharAt(_keyValues.lastIndex)
        }

    }

    /*
    This strategy is particularly useful for time series data. DateTieredCompactionStrategy stores data written within a certain period of time in the same SSTable. For example, Cassandra stores your last hour of data in one SSTable time window, and the next 4 hours of data in another time window, and so on. Cassandra performs compaction when the number of SSTables in those windows reaches min_threshold (4 by default). The most common queries for time series workloads retrieve the last hour/day/month of data. Cassandra can limit SSTables returned to those having the relevant data. Also, Cassandra can store data set to expire using TTL in an SSTable with other data scheduled to expire at approximately the same time. Cassandra can then drop the SSTable without doing any compaction.
     */
    class DateTieredCompactionStrategy(
            /*
            The size of the first time window.
             */
            var base_time_seconds: String = "3600",
            /*
            The size of the first time window.
             */
            var enabled: Boolean = true,
            /*
            The size of the first time window.
             */
            var log_all: Boolean = false,
            /*
            Cassandra stops considering an SSTable for compaction if all of its data is older than the specified number of days. The value can be a decimal number. This parameter is deprecated.
             */
            var max_sstable_age_days: String = "1000",
            /*
                The maximum window size in seconds. The default is 1 day.
             */
            var max_window_size_seconds: String = "86400",
            /*
            The maximum number of SSTables allowed in a minor compaction.
             */
            var max_threshold: String = "32",
            /*
            The minimum number of SSTables that trigger a minor compaction.
             */
            var min_threshold: String = "4",
            /*
            Set to MICROSECONDS or MILLISECONDS, to match the timestamp unit of the data you insert
             */
            var timestamp_resolution: String = "MICROSECONDS",
            /*
            The minimum number of seconds after an SSTable is created before Cassandra considers the SSTable for tombstone compaction. Cassandra starts tombstone compaction if the SSTable exceeds the tombstone_threshold.
             */
            var tombstone_compaction_interval: String = "86400",
            /*
             	The ratio of garbage-collectable tombstones to all contained columns. If the ratio exceeds this limit, Cassandra starts compaction on that table alone, to purge the tombstones.
             */
            var tombstone_threshold: String = "0.2",
            /*
            True allows Cassandra to run tombstone compaction without pre-checking which tables are eligible for this operation. Even without this pre-check, Cassandra checks an SSTable to make sure it is safe to drop tombstones.
             */
            var unchecked_tombstone_compaction: Boolean = false
    ) {

    }

    /*
    LeveledCompactionStrategy (LCS): Creates SSTables of a fixed, relatively small size (160 MB by default) that are grouped into levels. Within each level, SSTables are guaranteed to be non-overlapping. LCS uses STCS for the first level, called level 0 (L0). Each level beyond L0 (L1, L2 and so on) is 10 times as large as the previous level. Disk I/O is more uniform and predictable on higher than on lower levels as SSTables are continuously being compacted into progressively larger levels. At each level, row keys are merged into non-overlapping SSTables. This can improve performance for reads, because Cassandra can determine which SSTables in each level to check for the existence of row key data. This compaction strategy is modeled after Google's LevelDB implementation.
     */
    class LeveledCompactionStrategy(
            /*
            True enables background compaction.
             */
            var enabled: Boolean = true,
            /*
              True activates advanced logging for the entire cluster.
             */
            var log_all: Boolean = false,
            /*
            The target size for SSTables that use the Leveled Compaction Strategy. Although SSTable sizes should be less or equal to sstable_size_in_mb, it is possible tthat compaction may produce a larger SSTable during compaction. This occurs when data for a given partition key is exceptionally large. Cassandra does not splie the data into two SSTables.
             */
            var sstable_size_in_mb: String = "160MB",
            /*
            The minimum number of seconds after an SSTable is created before Cassandra considers the SSTable for tombstone compaction. Cassandra begins tombstone compaction SSTable's tombstone_threshold exceeds value of the following property.
             */
            var tombstone_compaction_interval: String = "86400",
            /*
            The ratio of garbage-collectable tombstones to all contained columns. If the ratio exceeds this limit, Cassandra starts compaction on that table alone, to purge the tombstones.
             */
            var tombstone_threshold: String = "0.2",

            /*
             	True allows Cassandra to run tombstone compaction without pre-checking which tables are eligible for this operation. Even without this pre-check, Cassandra checks an SSTable to make sure it is safe to drop tombstones.
             */
            var unchecked_tombstone_compaction: Boolean = false) {

    }
}