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
"message": "The user already has schedule between the dates"
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