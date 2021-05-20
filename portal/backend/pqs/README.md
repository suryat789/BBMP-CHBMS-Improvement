# Patient-Queuing-System

## Api Details.

* [Add or Update Patient](#add-or-update-patient)
* [Get Patient](#get-patient)
* [Move patient between queues](#move-patient-between-queues)
* [Enqueue a patient in queues](#enqueue-a-patient-in-queues)
* [Remove patient from a queue](#remove-patient-from-a-queue)
* [Remove a patient from all queues](#remove-a-patient-from-all-queues)
* [Get patient queue status](#get-patient-queue-status)
* [Get queue entries](#get-queue-entries)

## Add or Update Patient

#### POST /queuing-system/patients

Add a patient ( without giving patientId ) or update details of a patient.

Sample Request 

```json
{
  "idcpatient": 1234,
  "bucode": 23213,
  "pcnumber": "7887887123",
  "icmrNumber": "1234xabcdef1234",
  "srfNumber": "123456789",
  "pzone": "2"
}
```

Request Body fields.

``` typescript
- idcpatient ( Long, required, “patient ID”)
- bucode ( Long, optional,”BU number of the patient”)
- pcnumber (String, required, “patient contact number”)
- icmrNumber (String, optional, “ICMR number”)
- srfNumber (String, required, “SRF number”)
- pzone (String, required, “Patient Zone”)
```

Example :

http://host.com/queuing-system/patients

Response Status: CREATED 201 if newly created or ACCEPTED 202 if updated.

PS:
    
   * Same api can be used to update patient by providing the same patient id.
 
## Get Patient
#### GET /queuing-system/patients/:patientId

Get patient details by patient Id 

Sample Response

````json
{
  "idcpatient": 1234,
  "bucode": 23213,
  "pcnumber": "7887887123",
  "icmrNumber": "1234xabcdef1234",
  "srfNumber": "123456789",
  "pzone": "2"
}
````
Response fields

```
- idcpatient ( Long, required, “patient ID”)
- bucode ( Long, optional,”BU number of the patient”)
- pcnumber (String, required, “patient contact number”)
- icmrNumber (String, optional, “ICMR number”)
- srfNumber (String, required, “SRF number”)
- pzone (String, required, “Patient Zone”)
```

PS:
 * bucode, icmrNumber are optional.

## Move patient between queues

#### POST /queueing-system/patients/{PATIENT_ID}/removeAndAddToQueue

Sample Requests

```json
{
  "removeFromQueue": [
      "GENERAL",
      "ICU_VENTILATOR"
   ],
  "addToQueue": [
      "ER_ICU",
      "ER_REQUEST"
  ]
}
```

Response Status: 202 ACCEPTED

## Enqueue a patient in queues

#### POST /queueing-system/enqueue

patient will be added to queue if he is not in the queue. if he is already in the queue, no action.

Sample Requests 

````json
[
  "GENERAL",
  "ICU_VENTILATOR"
]
````

## Remove patient from a queue

#### DELETE /queueing-system/patients/{PATIENT_ID}/{QUEUE_ID}

patients if in the queue will be removed.

Response Status: 202 ACCEPTED

## Enqueue a patient in queues at front 

#### POST /queueing-system/enqueueAtFront

patient will be added ( even if he is in the queue but not in the pushed part ) to the pushed part

Sample Request

````json
[
  "GENERAL",
  "ICU_VENTILATOR"
]
````

Response Status: 202 ACCEPTED

## Remove a patient from all queues

#### DELETE /queueing-system/patients/{PATIENT_ID}

Patient will be removed from all queues.

Response Status: 202 ACCEPTED

## Get patient queue status

#### GET /queueing-system/patients/{PATIENT_ID}/status

- Here zone query param is optional.

Sample Response 

```json
[
 {
 "queueType": "ICU",
 "patientRank": 1,
 "enqueueTimestamp":
"2021-05-14T07:15:57.000+00:00"
 },
 {
 "queueType": "ICU_VENTILATOR",
 "patientRank": 1,
 "enqueueTimestamp":
"2021-05-14T07:21:08.000+00:00"
 }
]
```

## Get queue entries

#### GET /queueing-system/queues?queue=HDU,ER_ICU[&zone=4]

query params

queue - required - comma separated queue names

zone - optional - zone

Sample Response

```json
[
 {
 "id": 6,
 "type": "HDU",
 "patientId": 22,
 "enqueueTimestamp": "2021-05-14T07:15:57.000+00:00",
 "zone": "4"
 },
 {
 "id": 10,
 "type": "ER_ICU",
 "patientId": 21,
 "enqueueTimestamp": "2021-05-14T08:53:37.000+00:00",
 "zone": "4"
 }
]
```
  
