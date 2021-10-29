# Task Manager

**Goal**: Implement a task manager

Main definitions and assumptions:

- A task manager is a component that helps handling other processes.
- Each process is composed at least the following fields:
  - PID
  - Priority (low, medium, high)
  - Creation time
  - Command
- The task manager should be able handle a maximum number of processes
  defined at build time.

## Tasks

- [x] Add a task following the default behavior
- [x] Add a task following the FIFO behavior
- [ ] Add a task following the priority behavior
- [ ] List tasks by creation time
- [ ] List tasks by priority
- [ ] List tasks by PID
- [ ] Kill a specific process
- [ ] Kill all process with a specific priority
- [ ] Kill all running processes

### Extras

- [ ] Enable Java interoperability

# Development Notes

First, I need to create a data structure for tasks.

- [x] Create task definition
- [x] Create task manager definition

Now, I need to create a new namespace `task-manager.add-tasks` with functions
for each functionality:

- [x] Add tasks to task manager following the default behavior
- [x] Add tasks to task manager following the FIFO behavior
- [ ] Add tasks to task manager following the priority behavior
