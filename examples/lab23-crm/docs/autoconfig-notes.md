# Lab 23 — Auto-config vs ownership

## Three things Boot auto-configured
1. Embedded Tomcat server + DispatcherServlet (disappeared when we removed starter-web in Experiment 1)
2. Jackson JSON conversion for request/response bodies
3. Actuator health/info endpoints (exposed automatically once we set exposure.include)

## Three things you still own
1. Domain rule for duplicate customer IDs — Boot never enforces this, we saw it silently overwrite in Experiment 2
2. Missing-ID behavior — Boot's default is a 500, not the 404 a real API would want
3. Which Actuator endpoints to expose in prod vs dev (health-only in prod, more detail in dev)