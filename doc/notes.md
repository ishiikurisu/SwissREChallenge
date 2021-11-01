# Task Manager

**Goal**: Implement a task manager

Main definitions and assumptions:

- A task manager is a component that helps handling other processes.
- Each process is composed of at least the following fields, based on the
  requirements:
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

I defined a task as a hashmap with the required fields, while the
task manager is a list. I have decided for these data structures since they
will allow for straightforward implementation of algorithms in the future.
For example: the FIFO add strategy is simply a queue algorithm, which is
straightforward using a list, for example.

On a sidenote: I have picked an UUID as a PID because I imagine this might be
used by a database eventually and, while task managers use the next available
integer to display the task, this might not work well with a database.

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

Now that most of development is done, a few housekeeping would be
welcome:

- [x] Update README.md
- [x] List more development thoughts on these notes
- [x] Remove unnecessary files from repository

While reviewing the repository, I added another test to verify if the
code doesn't break when removing a task by a PID that's not in the list.
I feel like this is the kind of scenario that's expected from the tests.

After reviewing the code and the repository before evaluation, a couple of
doubts emerged:

- [ ] Find out if tasks should begin their commands (as in, start real
      threads)
- [ ] Find out if the main function should be a task manager UI
