(ns alphabet-cipher.coder)

(def alphabet
  (vec (map #(str (char %)) (range 97 123))))

(def matrix
  (mapv
    (fn [x] (concat (subvec alphabet (.indexOf alphabet x)) (subvec alphabet 0 (.indexOf alphabet x)))) alphabet))

(defn repeat-string [string n]
  (clojure.string/join (take n (apply str (repeat n string)))))

(defn match-cipher-and-message-in-matrix []
  (fn [k m]
    (nth (nth matrix (.indexOf (nth matrix 0) m)) (.indexOf (nth matrix 0) k))))

(defn get-encoded-message [cipher message]
    (clojure.string/join (map (match-cipher-and-message-in-matrix) cipher message)))

(defn encode [keyword message]
  (let [cipher (map str ( vec (repeat-string keyword (count message))))
        message (map str ( vec message)) ]

    (get-encoded-message cipher message)))

(defn decode [keyword message]
  "decodeme")
