(ns md-archiviofarmaco.model.farmaco
  (:require
    [md-archiviofarmaco.utils :as utils]
    [honeysql.core :as sql]
    [honeysql.helpers :as hh]))

(defn build-sqlmap
  [req params]
  (let [{:keys [minsan-codice minsan-descrizione atc-codice atc-descrizione
                classe piano-terapeutico coperto-brevetto dpc specialista ex-osp2
                stupefacenti in-commercio inappropriatezza equivalenza-aifa]}
        params]
     (-> (hh/select req)
         (hh/from :archivio_farmaco)
         (hh/merge-where (if-not (nil? minsan-codice) [:ilike :farmaco_minsan_cod (str minsan-codice "%")]))
         (hh/merge-where (if-not (nil? minsan-descrizione) [:ilike :farmaco_minsan_des (str "%" minsan-descrizione "%")]))
         (hh/merge-where (if-not (nil? atc-codice) [:ilike :farmaco_atc_cod (str atc-codice "%")]))
         (hh/merge-where (if-not (nil? atc-descrizione) [:ilike :farmaco_atc_des "%atc-descrizione%"]))
         (hh/merge-where (if-not (nil? classe) [:= :farmaco_pianoterapeutico classe]))
         (hh/merge-where (if-not (nil? piano-terapeutico) [:= :farmaco_pianoterapeutico 1]))
         (hh/merge-where (if-not (nil? coperto-brevetto) [:= :farmaco_brevetto 1]))
         (hh/merge-where (if-not (nil? dpc) [:= :farmaco_dpc 1]))
         (hh/merge-where (if-not (nil? specialista) [:= :farmaco_specialista 1]))
         (hh/merge-where (if-not (nil? ex-osp2) [:= :farmaco_exosp2 1]))
         (hh/merge-where (if-not (nil? stupefacenti) [:= :farmaco_stupefacente 1]))
         (hh/merge-where (if-not (nil? in-commercio) [:= :farmaco_commercio 1]))
         (hh/merge-where (if-not (nil? inappropriatezza) [:= :farmaco_interazioni 1]))
         (hh/merge-where (if-not (nil? equivalenza-aifa) [:= :farmaco_equivalenza_aifa equivalenza-aifa])))))

(defn select-by-params
  [params]
  (let [{:keys [start limit]} params
        q (-> (build-sqlmap :* params)
              (hh/order-by [:farmaco_minsan_des]))]
     (if (and start limit)
         (sql/build q :offset start :limit limit)
         q)))

(defn count-by-params
  [params]
  (build-sqlmap :%count.* params))


;;---------------- superfluo
(defn select-by-params-long
  [params]
  (let [{:keys [minsan-codice minsan-descrizione atc-codice atc-descrizione
                classe piano-terapeutico coperto-brevetto dpc specialista ex-osp2
                stupefacenti in-commercio inappropriatezza equivalenza-aifa
                start limit]}
        params
        q (-> (hh/select :*)
              (hh/from :archivio_farmaco)
              (hh/merge-where (if-not (nil? minsan-codice) [:ilike :farmaco_minsan_cod (str minsan-codice "%")]))
              (hh/merge-where (if-not (nil? minsan-descrizione) [:ilike :farmaco_minsan_des (str "%" minsan-descrizione "%")]))
              (hh/merge-where (if-not (nil? atc-codice) [:ilike :farmaco_atc_cod (str atc-codice "%")]))
              (hh/merge-where (if-not (nil? atc-descrizione) [:ilike :farmaco_atc_des "%atc-descrizione%"]))
              (hh/merge-where (if-not (nil? classe) [:= :farmaco_pianoterapeutico classe]))
              (hh/merge-where (if-not (nil? piano-terapeutico) [:= :farmaco_pianoterapeutico 1]))
              (hh/merge-where (if-not (nil? coperto-brevetto) [:= :farmaco_brevetto 1]))
              (hh/merge-where (if-not (nil? dpc) [:= :farmaco_dpc 1]))
              (hh/merge-where (if-not (nil? specialista) [:= :farmaco_specialista 1]))
              (hh/merge-where (if-not (nil? ex-osp2) [:= :farmaco_exosp2 1]))
              (hh/merge-where (if-not (nil? stupefacenti) [:= :farmaco_stupefacente 1]))
              (hh/merge-where (if-not (nil? in-commercio) [:= :farmaco_commercio 1]))
              (hh/merge-where (if-not (nil? inappropriatezza) [:= :farmaco_interazioni 1]))
              (hh/merge-where (if-not (nil? equivalenza-aifa) [:= :farmaco_equivalenza_aifa equivalenza-aifa]))
              (hh/order-by [:farmaco_minsan_des]))]
     (if (and start limit)
         (sql/build q :offset start :limit limit)
         q)))
