# SwissRE Challenge

Task: develop a task manager.

This was done using the [Clojure](https://clojure.org/) programming language.

## Installation

- Ensure you have the latest version of the
  [Java Development Kit](https://java.com/en/download/help/develop.html)
  installed.
- Install [Leiningen](https://leiningen.org/).

## Usage

Currently, this task only contains unit tests, which can be run as follows:

``` sh
lein test
```

## Examples

The core of the project is the `task-manager` package, which defines
a couple of functions as a contract for the requirements of the challenge.
Namely:

- [`task-manager.utils`](src/task_manager/utils.clj) includes constants and
  utility functions for this project.
  - In particular, the total number of tasks `total-tasks` is defined as a
    constant here.
- The [`task-manager.model`](src/task_manager/model.clj) namespace defines the
  data structures for manipulation:
  - `task`s are defined as a hash map with the following fields:
    - `pid`: an UUID to identify the current task
    - `priority`: can be picked as either `:low`, `:medium`, or `:high`.
      Internally, it will be assigned to a number using the `utils/get-priority`
      function. The smaller this number, the higher the priority.
    - `creation-time`: timestamp of task creation
    - `command`: a string with the desired command to run
  - the `task-manager` itself, which is currently being treated as a list
- The [`task-manager.add-task`](src/task_manager/add_task.clj) namespace defines
 functions to add tasks to the task manager using the following algorithms:
  - `default-add` only adds a task if there is room in the task manager
  - `fifo-add` clears space for new tasks by deleting the oldest ones
  - `priority-add` only adds tasks give they have enough priority when compared
    to the existing ones.
- The [`task-manager.list-task`](src/task_manager/list_task.clj) namespace lists
  tasks using the required strategies: `by-creation-time`, `by-priority`, and
  `by-pid`.
- The [`task-manager.kill-task`](src/task_manager/kill_task.clj) namespace
  includes functions to remove tasks from the task manager, namely `by-pid`,
  `by-priority`, or kill `all` tasks.
  - As a known behavior, since tasks do not execute their commands, this only
    means removing `task`s from the `task-manager` list.

## Known Bugs and Quirks

- Java interoperation is not yet properly implemented.
- The task manager doesn't start real system tasks.
- Compiling the application with `lein uberjar` will generate a simple `Hello World!`.

## License

Copyright © 2021 Cristiano Silva Jr.

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
