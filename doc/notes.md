# Task Manager

**Goal**: Implement a task manager

Main definitions and assumptions:

- A task manager is a component that helps handling other processes.
- Each process is composed at least the following fields:
  - PID
  - Priority (low, medium, high)
  - Creation time
  - Task definition
- The task manager should be able handle a maximum number of processes
  defined at build time.

## Tasks

- [ ] Add a task following the default behavior
- [ ] Add a task following the FIFO behavior
- [ ] Add a task following the priority behavior
- [ ] List tasks by creation time
- [ ] List tasks by priority
- [ ] List tasks by PID
- [ ] Kill a specific process
- [ ] Kill all process with a specific priority
- [ ] Kill all running processes
