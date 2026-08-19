# Testing Steps — Simple Distance Tracker

1. Run the app on an emulator.
2. Open Emulator → Extended Controls (⋮ icon) → Location.
3. Enter coordinates for Point A, click "Set Location".
4. In the app, tap "Set Start Point" → confirm start coordinates appear.
5. Back in Extended Controls, enter coordinates for Point B, click "Set Location".
6. In the app, tap "End Point" → confirm end coordinates and a non-zero distance appear.
7. Test the guard case: reinstall/restart the app, tap "Set End Point" FIRST without setting a start point → confirm it shows a warning message and does not crash.

## Demo Script (3 minutes)
- Explain the app's purpose (10s)
- Live demo: set start, change mock location, set end, show distance (90s)
- Explain the permission flow: check → request → handle result (30s)
- Show the guard case briefly (20s)