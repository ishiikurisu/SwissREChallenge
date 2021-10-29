(ns task-manager.utils)

(def total-tasks 5)

(defn uuid [] (.toString (java.util.UUID/randomUUID)))
