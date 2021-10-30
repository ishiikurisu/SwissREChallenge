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
- [x] Add a task following the priority behavior
- [x] List tasks by creation time
- [x] List tasks by priority
- [x] List tasks by PID
- [x] Kill a specific process
- [x] Kill all process with a specific priority
- [x] Kill all running processes

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
- [x] Add tasks to task manager following the priority behavior

Next step is to list tasks.

- [x] List tasks by creation time
- [x] List tasks by priority
- [x] List tasks by PID

Next step is to kill processes:

- [x] Kill process by PID
- [x] Kill all processes with a specific priority
- [x] Kill all running processes

Something else: I think I spotted a bug on the FIFO addition.
The implementation is currently the same as the default behavior,
which doesn't seem right.

- [x] Assure FIFO addition is respected
