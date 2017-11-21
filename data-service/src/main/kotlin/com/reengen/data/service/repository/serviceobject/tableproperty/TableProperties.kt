package com.reengen.data.service.repository.serviceobject.tableproperty

import com.reengen.data.service.repository.abstraction.ICompactionStrategy
import com.reengen.data.service.repository.abstraction.ISubPropertyObject
import com.reengen.data.service.repository.abstraction.ITable
import com.reengen.data.service.repository.abstraction.ITableProperty
import com.sun.org.apache.bcel.internal.generic.ISUB

class TableProperties {

    /*
    The Bloom filter sets the false-positive probability for SSTable Bloom filters. When a client requests data, Cassandra uses the Bloom filter to check if the row exists before doing disk I/O. Bloom filter property value ranges from 0 to 1.0. Lower Bloom filter property probabilities result in larger Bloom filters that use more memory. The effects of the minimum and maximum values:
0: Enables the unmodified, effectively the largest possible, Bloom filter.
1.0: Disables the Bloom filter. Recommended setting: 0.1. A higher value yields diminishing returns.
     */
    class Bloom_Filter_FP_Chance(
            private var keys: String = "ALL",
            private var rows_per_partition: String = "NONE") {

        var _bffc: StringBuilder = StringBuilder().append("keys : " + this.keys.toString()).append("rows_per_partition : " + this.rows_per_partition.toString())
    }
    /*
    To give a human readable comment to the table
     */
    class Comment(private var message :String = "plain table" ){

        var _comment:StringBuilder = StringBuilder()

    }
    /*
    The probability that a successful read operation triggers a read repair. Unlike the repair controlled by read_repair_chance, this repair is limited to replicas in the same DC as the coordinator. The value must be between 0 and 1.

    Cassandra performs read repair whenever a read reveals inconsistencies among replicas. You can also configure Cassandra to perform read repair after a completely consistent read. Cassandra compares and coordinates all replicas, even those that were not accessed in the successful read. The probability that a consistent read of a table triggers a read repair is set by dclocal_read_repair_chance and read_repair_chance. The first of these properties sets the probability for a read repair that is confined to the same datacenter as the coordinator node. The second property sets the probability for a read repair across all datacenters that contain matching replicas. This cross-datacenter operation is much more resource-intensive than the local operation.

    Recommendations: if the table is for time series data, both properties can be set to 0 (zero). For other tables, the more performant strategy is to set dc_local_read_repair_chance to 0.1 and read_repair_chance to 0. If you want to use read_repair_chance, set this property to 0.1.

    The Bloom filter sets the false-positive probability for SSTable Bloom filters. When a client requests data, Cassandra uses the Bloom filter to check if the row exists before doing disk I/O. Bloom filter property value ranges from 0 to 1.0. Lower Bloom filter property probabilities result in larger Bloom filters that use more memory. The effects of the minimum and maximum values:
0: Enables the unmodified, effectively the largest possible, Bloom filter.
1.0: Disables the Bloom filter. Recommended setting: 0.1. A higher value yields diminishing returns.
     */
    class dlocal_read_repair_chance(private var value :String){

    }
    /*
    The probability that a successful read operation will trigger a read repair.of read repairs being invoked. Unlike the repair controlled by dc_local_read_repair_chance, this repair is not limited to replicas in the same DC as the coordinator. The value must be between 0 and 1
     */
    class read_repair_chance(private var value: String){

    }

    /*
    Time-to-live (TTL) is an optional expiration date for values that are inserted into a column. Also see Expiring columns in the relevant CQL documentation.

    Set this property in MapReduce scenarios when you have no control of TTL. The value of this property is a number of seconds. If it is set, Cassandra applies a default TTL marker to each column in the table, set to this value. When the table TTL is exceeded, Cassandra tombstones the table.
Note: You can effectively delete any column TTLs in a table by setting the default_time_to_live to zero.
     */
    class default_time_to_live (private var value: String){

    }
    /*
    The default value or gc_grace_seconds is 864000 seconds (10 days). In a single-node cluster, this property can safely be set to zero. This value can also be reduced for tables whose data will not be explicitly deleted — for example, tables containing only data with TTL set, or tables with default_time_to_live set. However, if you lower the gc_grace_seconds value, consider its interaction with these operations:

    hint replays — When a node goes down and then comes back up, other nodes replay the write operations (called hints) that are queued for that node while it was unresponsive. Cassandra does not replay hints older than gc_grace_seconds after creation. The max_hint_window_in_ms setting in the cassandra.yaml file sets the time limit (3 hours by default) for collecting hints for the unresponsive node.
    batch replays — Like hint queues, batch operations store database mutations that are replayed in sequence. As with hints, Cassandra does not replay a batched mutation until gc_grace_seconds after it was created. If your application uses batch operations, consider the possibility that decreasing gc_grace_seconds increases the chance that a batched write operation may restore deleted data. The batchlog_replay_throttle_in_kb property in the cassandra.yaml file gives some control of the batch replay process. The most important factors, however, are the size and scope of the batches you use.

     */
    class gc_grace_seconds(private var value: String){

    }
    /*
    The minimum gap between index entries in the index summary. A lower min_index_interval means the index summary contains more entries from the index, which allows Cassandra to search fewer index entries to execute a read. A larger index summary may also use more memory. The value for min_index_interval is the densest possible sampling of the index.
     */
    class min_index_interval(private  var value: String){

    }
    /*
   If the total memory usage of all index summaries reaches this value, Cassandra decreases the index summaries for the coldest SSTables to the maximum set by max_index_interval. The max_index_interval is the sparsest possible sampling in relation to memory pressure.
    */
    class max_index_interval(private  var value: String){

    }
    /*
   To control the sampling of entries from the partition index, configure the sample frequency of the partition summary by changing these properties.
   */
    class index_interval(private  var value: String){

    }
    /*
    The number of milliseconds before Cassandra flushes memtables associated with this table.
     */
    class memtable_flush_period_in_ms(private var value: String){

    }
    /*
    Overrides normal read timeout when read_repair_chance is not 1.0, sending another request to read.
     */
    class speculative_retry(private var value: String){

    }
    /*
    Caching optimizes the use of cache memory by a table without manual tuning. Cassandra weighs the cached data by size and access frequency. Coordinate this setting with the global caching properties in the cassandra.yaml file. See Cassandra 3.0 documentation.
Configure the cache by creating a property map of values for the caching property. Options:

    keys: ALL or NONE
    rows_per_partition: number of CQL rows (N), NONE, or ALL

According to the rows_per_partition value, Cassandra caches only the first N rows in a partition, as determined by the clustering order.
     */
    class caching {

    }
    /*
    The compression algorithm. Valid values are LZ4Compressor), SnappyCompressor, and DeflateCompressor.
     */
    class compression {

    }
    /*
 The compaction property defines the compaction strategy class for this table. Choose the compaction strategy that best fits your data and environment.
  */
    class Compaction(stcs: TableSubProperties.SizeTieredCompactionStrategy) {

        private val _props: StringBuilder = StringBuilder().append("{").append(stcs.ToString()).append("}")


         override fun toString(): String {
            return _props.toString()
        }

        private fun createPropKeyValueStrings(){

        }
    }

}