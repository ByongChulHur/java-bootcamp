Command:
java -XX:+UseZGC -Xms16m -Xmx64m -Xlog:gc GcObserve

Evidence:
The log began with "Using The Z Garbage Collector" instead of "Using G1".
G1's log repeatedly showed "Pause Young ... (G1 Evacuation Pause)", while ZGC's
log used different terms like "Minor Collection" and "Major Collection", and
never reported an "Evacuation Pause". ZGC also had a distinct "Warmup" phase
at the start that G1 did not have.