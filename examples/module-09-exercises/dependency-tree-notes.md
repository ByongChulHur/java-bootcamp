Step 1 — Classify rows
Artifact	        Direct or transitive?	Scope shown
junit-jupiter	        Direct                 test
junit-jupiter-params	Transitive             test


Step 4 — CI command habit

What does -B mean?	Batch mode — less interactive prompts, friendlier for CI logs
Why verify instead of casual install on every push?	Proves package + checks without writing into every agent’s ~/.m2 unless the pipeline intentionally installs
Preferred CI-style command for this bootcamp	mvn -B verify

Write one sentence you could put in a README:

Teammates and CI should reproduce the build with mvn -B verify.