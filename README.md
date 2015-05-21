# childrenLab


# www.childrenLab.com/api/login
* Params - email, password
* Return example: 
```
{
"username": "test@test.com",
"roles": [
"ROLE_USER"
],
"access_token": "4ncvvg81j5smer66avrgdasgvcncngd3",
"token_type": "Bearer"
}
```

* Exception: if email or password not match, the response status will be 403 Unauthorized (let me know if you don't like it...)

# www.childrenLab.com/user/register
* Params(required) - email, password, phoneNumber, firstName, lastName
* other Params - birthday, nickName, sex, address, city, zipCode, role(2 type: ROLE_USER, ROLE_NANNY)
* The default role is ROLE_USER

* Exception example: 
```
{
"success": false,
"message": "The email already registered."
}
```

# www.childrenLab.com/schedule/create
* Params(required) - startDate, endDate (format: yyyy-MM-dd hh:mm), paymentPerHour
* other Params - status(^PENDING,PUBLIC,COMPLETED,REMOVED), type(NANNY, PARENT), note
* The default status is PENDING

* Exception example:
if the user already has schedule during the dates
```
{
"success": false,
"message": "You already has schedule between the dates"
}
``` 
when create successfully
```
{
"success": true
}
```

# www.childrenLab.com/schedule/edit
* Params - only scheduleId is required
* Same as create, but can't edit status, and type

* Exception example:
if the user already has schedule during the dates
```
{
"success": false,
"message": "You already has schedule between the dates"
}
```
when create successfully
```
{
"success": true
}
```

# www.childrenLab.com/schedule/updateStatus
* Params(required) - scheduleId, status (PENDING,PUBLIC,COMPLETED,REMOVED)
* This API is able to change schedule status, even for deleting schedule, just change the status = REMOVED

* Exception example:
if the something wrong when update
```
{
"success": false,
"message": "Can't find schedule."
}
``` 
when update successfully
```
{
"success": true
}
```

# www.childrenLab.com/schedule/retrieveUserSchedule
* Params - scheduleId
* If you call this API with scheduleId, it will return only one schedule and its message list
* If you call this without scheduleId, it will return all of schedule that related to the login user

* Return example:
```
{
"success": true,
"schedule": [
{
"id": 1,
"dateCreated": "2015-03-10 07:35:23",
"lastUpdated": "2015-03-13 05:33:43",
"startDate": "2015-02-01 12:00:00",
"endDate": "2015-03-01 12:00:00",
"status": "REMOVED",
"type": "NANNY",
"user": "J C",
"userId": 4
},
{
"id": 2,
"dateCreated": "2015-03-10 07:37:59",
"lastUpdated": "2015-03-10 07:37:59",
"startDate": "2015-01-31 12:00:00",
"endDate": "2015-03-01 12:00:00",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 4
}
]
}
```
With message:
```
{
"success": true,
"schedule": {
"id": 1,
"dateCreated": "2015-03-15 02:22:14",
"lastUpdated": "2015-03-15 02:22:14",
"startDate": "2015-02-04 11:00:00",
"endDate": "2015-03-01 09:00:00",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 3
},
"message": [
{
"class": "childrenlab.ScheduleMessage",
"id": 3,
"dateCreated": "2015-03-14T18:24:06Z",
"lastUpdated": "2015-03-14T18:24:06Z",
"message": "I'm herererererer",
"schedule": {
"class": "childrenlab.Schedule",
"id": 1
},
"user": {
"class": "childrenlab.User",
"id": 3
}
},
{
"class": "childrenlab.ScheduleMessage",
"id": 2,
"dateCreated": "2015-03-14T18:23:48Z",
"lastUpdated": "2015-03-14T18:23:48Z",
"message": "Testing test",
"schedule": {
"class": "childrenlab.Schedule",
"id": 1
},
"user": {
"class": "childrenlab.User",
"id": 3
}
},
{
"class": "childrenlab.ScheduleMessage",
"id": 1,
"dateCreated": "2015-03-14T18:23:03Z",
"lastUpdated": "2015-03-14T18:23:03Z",
"message": "Testing test",
"schedule": {
"class": "childrenlab.Schedule",
"id": 1
},
"user": {
"class": "childrenlab.User",
"id": 3
}
}
]
}
```
# www.childrenLab.com/schedule/list
* This return all of schedule (Not sure if you need it)

* Return example:
```
{
"success": true,
"list": [
{
"id": 1,
"dateCreated": "2015-03-10 07:35:23",
"lastUpdated": "2015-03-13 05:33:43",
"startDate": "2015-02-01 12:00:00",
"endDate": "2015-03-01 12:00:00",
"status": "REMOVED",
"type": "NANNY",
"user": "J C",
"userId": 4
},
{
"id": 2,
"dateCreated": "2015-03-10 07:37:59",
"lastUpdated": "2015-03-10 07:37:59",
"startDate": "2015-01-31 12:00:00",
"endDate": "2015-03-01 12:00:00",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 4
}
]
}
```

# www.childrenLab.com/schedule/search
* Params(required) - startDate, endDate (format: yyyy-mm-dd hh:mm), startPrice, endPrice, gender
* Params(not required) - offset, max, zipcode

* Return example:
```
{
"success": true,
"scheduleList": [
{
"id": 3,
"dateCreated": "2015-03-15 02:53:17",
"lastUpdated": "2015-03-15 02:53:17",
"startDate": "2015-07-04 12:00:00",
"endDate": "2015-07-05 05:00:00",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 3
},
{
"id": 2,
"dateCreated": "2015-03-15 02:51:12",
"lastUpdated": "2015-03-15 02:51:12",
"startDate": "2015-05-04 12:00:00",
"endDate": "2015-05-05 05:00:00",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 3
},
{
"id": 1,
"dateCreated": "2015-03-15 02:22:14",
"lastUpdated": "2015-03-15 02:22:14",
"startDate": "2015-02-04 11:00:00",
"endDate": "2015-03-01 09:00:00",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 3
}
],
"totalSize": 3
}
```

# www.childrenLab.com/kids/add
* Params(required) - firstName, lastName, birthday(Format must be: "yyyy-MM-dd"
* Params(not required) - nickName, note

* Return example:
```
{
"success": true
}
```

# www.childrenLab.com/kids/edit
* Params(required) - kidId
* Params(not required) - firstName, lastName, birthday, nickName, note

* Return example:
```
{
"success": true
}
```

# www.childrenLab.com/kids/remove
* Params(required) - kidId

* Return example:
```
{
"success": true
}
```

# www.childrenLab.com/kids/list
* Return example:
```
{
    "success": true,
    "kids": [
        {
            "id": 2,
            "firstName": "test2",
            "lastName": "testLast2",
            "nickName": "testNick2",
            "birthday": "1985-09-30 12:00:00",
            "note": "hahaha"
        },
        {
            "id": 1,
            "firstName": "test",
            "lastName": "testLast",
            "nickName": "testNick",
            "birthday": "1985-08-29 12:00:00",
            "note": "hahaha, it's test"
        }
    ]
}
```