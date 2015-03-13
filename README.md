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
* If you call this API with scheduleId, it will return only one schedule
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