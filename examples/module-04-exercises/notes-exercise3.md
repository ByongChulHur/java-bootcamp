The program allocated about 250 MB over time despite a 64 MB maximum heap.
GC log entries appeared between rounds. For example:
GC(32) Pause Full (G1 Compaction Pause) 63M->10M(37M) 4.951ms
This before/after drop (63M -> 10M) shows that memory was reclaimed.
Exact pause times varied on my machine.