(ns md-archiviofarmaco.utils
  (:require
    [clojure.set :as set]
    [camel-snake-kebab.core :as csk]))

(defn camel->kebab
   [xs]
   (map #(csk/->kebab-case %) xs))

(defn rename-keys-camel->kebab
  [mymap]
  (let [kmap (into (sorted-map) (map #(hash-map % (csk/->kebab-case %)) (keys mymap)))]
   (set/rename-keys mymap kmap)))
