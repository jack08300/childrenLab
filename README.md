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

* Exception: if email or password not match, the response status will be 401 Unauthorized (let me know if you don't like it...)

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

# www.childrenLab.com/schedule/list
* This return all of schedule (Not sure if you need it)

* Return example:
```
{
"success": true,
"list": [
{
"id": 1,
"dateCreated": "2015-03-09T23:35:23Z",
"lastUpdated": "2015-03-12T21:33:43Z",
"startDate": "2015-01-31T16:00:00Z",
"endDate": "2015-02-28T16:00:00Z",
"status": "REMOVED",
"type": "NANNY",
"user": "J C",
"userId": 4
},
{
"id": 2,
"dateCreated": "2015-03-09T23:37:59Z",
"lastUpdated": "2015-03-09T23:37:59Z",
"startDate": "2015-01-30T16:00:00Z",
"endDate": "2015-02-28T16:00:00Z",
"status": "PRIVATE",
"type": "NANNY",
"user": "J C",
"userId": 4
}
]
}
```