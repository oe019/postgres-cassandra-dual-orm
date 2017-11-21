package com.reengen.data.service.repository.enum

enum class TableProperyTypes {
    /*
    <http://docs.datastax.com/en/cql/3.1/cql/cql_reference/tabProp.html>
    Description: Desired false-positive probability for SSTable Bloom filters, see <docs.datastax.com/en/cql/3.1/cql/cql_reference/tabProp.html#tabProp_modeBloomFilter
    Default: for SizeTieredCompactionStrategy : 0.01, for DateTieredCompactStrategy : 0.1 , for LeveledCompactionStrategy: 0.1
     */
    bloom_filter_fp_chance,
    /*
    Description: Optimizes the use of cache memory without manual tuning.
    Default: ALL for keys NONE for rows_per_partition; Cassandra 2.0.x: keys_only
     */
    caching,
    /*
    Description: A human readable comment describing the table.
    Default: N/A
     */
    comment,
    /*
    Description: Sets the compaction strategy for the table.
    Default: SizeTieredCompactionStrategy
     */
    compaction,
    /*
    Description: The compression algorithm. Valid values are LZ4Compressor), SnappyCompressor, and DeflateCompressor.
    Default: LZ4Compressor
     */
    compression,
    /*
    Description:The probability that a successful read operation triggers a read repair. Unlike the repair controlled by read_repair_chance, this repair is limited to replicas in the same DC as the coordinator. The value must be between 0 and 1
    Default: In Cassandra 2.1, Cassandra 2.0.9 and later: 0.1, in Cassandra 2.0.8 and earlier: 0.0
     */
    dclocal_read_repair_chance,

    /*
    Description: Set this property in MapReduce scenarios when you have no control of TTL. The value of this property is a number of seconds. If it is set, Cassandra applies a default TTL marker to each column in the table, set to this value. When the table TTL is exceeded, Cassandra tombstones the table.
    Note: You can effectively delete any column TTLs in a table by setting the default_time_to_live to zero.
    Default: 0
     */
    default_time_to_live,
    /*
    Description: The number of seconds after data is marked with a tombstone (deletion marker) before it is eligible for garbage-collection. Cassandra will not execute hints or batched mutations on a tombstoned record within its gc_grace_period. The default value allows a great deal of time for Cassandra to maximize consistency prior to deletion. For details about decreasing this value, see garbage collection below.
    Default: 864000 [10 days]
     */
    gc_grace_seconds,
    /*
    Description: To control the sampling of entries from the partition index, configure the sample frequency of the partition summary by changing these properties.
    Default: min_index_interval 128
     */
    min_index_interval,
    /*
     Description: Cassandra 2.1.x; To control the sampling of entries from the partition index, configure the sample frequency of the partition summary by changing these properties.
     Default: max_index_interval 2048
     note: The minimum gap between index entries in the index summary. A lower min_index_interval means the index summary contains more entries from the index, which allows Cassandra to search fewer index entries to execute a read. A larger index summary may also use more memory. The value for min_index_interval is the densest possible sampling of the index.
   */
    max_index_interval,
    /*
    Description: Cassandra 2.0.x; To control the sampling of entries from the partition index, configure the sample frequency of the partition summary by changing these properties.
    Default: index_interval 128
    note: If the total memory usage of all index summaries reaches this value, Cassandra decreases the index summaries for the coldest SSTables to the maximum set by max_index_interval. The max_index_interval is the sparsest possible sampling in relation to memory pressure.
     */
    index_interval,
    /*
    Description: The number of milliseconds before Cassandra flushes memtables associated with this table.
    Default: 0
     */
    memtable_flush_period_in_ms,
    /*
    Description: The probability that a successful read operation will trigger a read repair.of read repairs being invoked. Unlike the repair controlled by dc_local_read_repair_chance, this repair is not limited to replicas in the same DC as the coordinator. The value must be between 0 and 1.
     Default: 0.0
     */
    read_repair_chance,
    /*
    Description: Overrides normal read timeout when read_repair_chance is not 1.0, sending another request to read.
    Default:99percentile Cassandra 2.0.2 and later
     */
    speculative_retry

}