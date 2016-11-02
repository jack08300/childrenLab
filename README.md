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

* Exception: if email or password not match, the response status will be 403 Unauthorized
* Use the access_token to access other API. Put the token on the request header.
Ex:
```
 x-auth-token: 4ncvvg81j5smer66avrgdasgvcncngd3
```


# user/register
* Params(required) - email, password, phoneNumber, firstName, lastName
* other Params - address, city, zipCode
* Type - email: String, password: String ( > 6), phoneNumber: String, firstName: String, LastName: String, address: String, city: String, zipCode: String

* Return example: 
```
{
"success": false,
"message": "The email already registered."
}
```

# user/updateProfile
* Params(Not required) - address, city, state, zipCode, email, password, firstName, lastName

* Return example: 
```
{
  "success": true,
  "user": {
    "id": 3,
    "firstName": "Jay1",
    "lastName": "chen1",
    "nickName": null,
    "birthday": null,
    "phoneNumber": "3020102031",
    "email": "jack08300@gmail.com",
    "profile": null,
    "address": null,
    "city": "Here",
    "state": "NY",
    "zipCode": "11342"
  }
}
```

# user/retrieveUserProfile

* Return example: 
```
{
  "success": true,
  "user": {
    "id": 3,
    "firstName": "Jay1",
    "lastName": "chen1",
    "nickName": null,
    "birthday": null,
    "phoneNumber": "3020102031",
    "email": "jack08300@gmail.com",
    "profile": null,
    "address": null,
    "city": "Here",
    "state": "NY",
    "zipCode": "11342"
  },
  "kids": []
}
```

# user/isEmailRegistered
* Params - email
* Return example:
```
{
  "registered": true
}
```

# kids/add
* Params(required) - firstName, lastName, birthday(Format must be: "yyyy-MM-dd"
* Params(not required) - nickName, note

* Type - birthday: Formatted String, firstName: String, LastName: String, nickName: String, note: String

* Return example:
```
{
"success": true
}
```

# kids/edit
* Params(required) - kidId
* Params(not required) - firstName, lastName, birthday, nickName, note

* Return example:
```
{
"success": true
}
```

# kids/remove
* Params(required) - kidId

* Return example:
```
{
"success": true
}
```

# kids/list
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

# avatar/temp
* Temp cloud place to store image
* Params(required) - img
* It return image byte format: data:image/png;base64

# avatar/uploadProfileImage
* Profile Image for user
* The image stored in http://avatar.childrenlab.com/'
* Params(required) - encodedImage (we should change it)
* Return example:
```
{
    "success": true,
    "profileImage": "1234521.png"
}
```

# avatar/uploadKidsProfileImage
* Profile Image for user
* The image stored in http://avatar.childrenlab.com/'
* Params(required) - encodedImage (we should change it), kidId
* Return example:
```
{
    "success": true,
    "profileImage": "1234521.png"
}
```

# calendarEvent/addEvent
* Event creation
* Params(required) - eventName, startDate, endDate, color, status, description, alert, timezoneOffset
* Other Params - city, state
* Type - eventName: String, startDate: Formatted String, endDate: Formatted String, color: String, status: String, description: String, alert: Int, timezoneOffset: Int
* Status Value: Open, Pass, Cancel
* Color: yellow, purple, green, pink, orange, grey
* startDate and endDate format: yyyy/MM/dd HH:mm:ss
* Return example:
```
{
  "success": true,
  "newEvent": {
    "id": 195,
    "eventName": "Test",
    "startDate": "2016-11-03 19:02:30",
    "endDate": "2016-11-05 20:22:33",
    "color": "blue",
    "status": "Open",
    "description": "Tester ididi",
    "alert": 0,
    "city": null,
    "state": null,
    "timezoneOffset": 240,
    "todo": null
  }
}
```

# calendarEvent/addTodo
* Create todo under calendar event
* Params(required) - eventId, todoList
* Type - eventId: int, todoList: String
* todoList format - each todo separate with | , Example:
```
todoList = Take a picture|Walk for 30 minutes|Take key
```
* Return example:
```
{
  "success": true,
  "event": {
    "id": 2,
    "eventName": "test1",
    "startDate": "2015-10-10 03:12:12",
    "endDate": "2015-12-01 12:12:12",
    "color": "blue",
    "status": "Open",
    "description": "",
    "alert": 0,
    "city": null,
    "state": null
  },
  "todo": [
    {
      "id": 29,
      "text": "No",
      "status": "PENDING"
    },
    {
      "id": 35,
      "text": "No",
      "status": "PENDING"
    },
    {
      "id": 38,
      "text": "No",
      "status": "PENDING"
    }
  ]
}
```
# calendarEvent/todoDone
* Done todo
* Params - todoId
* Return example:
```
{
    "success": true
}
```

# calendarEvent/deleteTodo
* Params - todoId, eventId
* Return example:
```
{
    "success": true
}
```

# calendarEvent/getEventsByUser
* Event creation
* Params - query, month, year, day
* query - month or day.
* If query == month, only month and year are required from parameters.
* If query == day, month, year, and day are required from parameters.
* month - integer
* year - integer
* day - integer
* Return example:
```
{
  "success": true,
  "events": [
    {
      "id": 17,
      "eventName": "Jehcd",
      "startDate": "2016-07-06 09:39:00",
      "endDate": "2016-07-06 09:40:00",
      "color": "pink",
      "status": "Open",
      "description": "",
      "alert": 43
    },
    {
      "id": 18,
      "eventName": "Tfvg",
      "startDate": "2016-07-06 09:42:00",
      "endDate": "2016-07-06 09:43:00",
      "color": "green",
      "status": "Open",
      "description": "",
      "alert": 48
    }
  ],
  "totalCount": 2
}
```

# calendarEvent/getEvent
* Params - eventId
* Return example:
```
{
  "success": true,
  "event": {
    "id": 4,
    "eventName": "test1",
    "startDate": "2016-08-10 03:12:12",
    "endDate": "2016-08-12 12:12:12",
    "color": "blue",
    "status": "Open",
    "description": "",
    "alert": 0,
    "city": "Taipei",
    "state": "test",
    "todo": [
      {
        "id": 40,
        "text": "YEs",
        "status": "PENDING"
      },
      {
        "id": 42,
        "text": "You too",
        "status": "PENDING"
      }
    ]
  }
}
```

# calendarEvent/editEventWithTodo
* Params(required) - id, eventName, startDate, endDate, color, description, alert, city, state, todoList
* startDate and endDate format: yyyy/MM/dd HH:mm:ss
* Send all of todos belong to this event, joined with |
* todoList format: test1|test2|test3
* Return example:
```
{
  "success": true,
  "event": {
    "id": 3,
    "eventName": "是中文喔!!",
    "startDate": "2015-10-10 03:12:12",
    "endDate": "2015-12-01 12:12:12",
    "color": "Black",
    "status": "Open",
    "description": "",
    "alert": 5,
    "city": "台北",
    "state": "test",
    "todo": [
      {
        "id": 9,
        "text": "YES",
        "status": "PENDING"
      },
      {
        "id": 7,
        "text": "希望可以",
        "status": "PENDING"
      },
      {
        "id": 8,
        "text": "過的了",
        "status": "PENDING"
      }
    ]
  }
}
```


# calendarEvent/deleteEvent
* Params(required) - id
* Return example:
```
{
    "success": true
}
```

# device/uploadRawData
* Params(required) - indoorActivity, outdoorActivity, time, macId
* time is "time stamp"
* Raw Data Example: 
```
indoorActivity:  1471185427,1,232,0,0,2,0,0,0,3,0,0,0,4,0,0,0,0
outdoorActivity: 1471185427,0,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1
time:            1470885849
macId:           tester1
```
* Return example:
```
{
    "success": true
}
```

# device/getDailyActivity
* Params(required) - macId
* For now, it only support today
* Return example:
```
{
  "success": true,
  "activity": [
    {
      "steps": 242,
      "date": "2016-08-21",
      "type": "INDOOR"
    },
    {
      "steps": 0,
      "date": "2016-08-21",
      "type": "OUTDOOR"
    },
    {
      "steps": 1096,
      "date": "2016-08-23",
      "type": "INDOOR"
    },
    {
      "steps": 482,
      "date": "2016-08-23",
      "type": "OUTDOOR"
    }
  ],
  "query": "monthly"
}
```

# device/getWeeklyActivity
* Params(required) - macId
* For now, it only support current week
* Return example:
```
{
  "success": true,
  "activity": [
    {
      "steps": 242,
      "date": "2016-08-21",
      "type": "INDOOR"
    },
    {
      "steps": 0,
      "date": "2016-08-21",
      "type": "OUTDOOR"
    },
    {
      "steps": 1096,
      "date": "2016-08-23",
      "type": "INDOOR"
    },
    {
      "steps": 482,
      "date": "2016-08-23",
      "type": "OUTDOOR"
    }
  ],
  "query": "monthly"
}
```

# device/getMonthlyActivity
* Params(required) - macId
* For now, it only support current month
* Return example:
```
{
  "success": true,
  "activity": [
    {
      "steps": 242,
      "date": "2016-08-21",
      "type": "INDOOR"
    },
    {
      "steps": 0,
      "date": "2016-08-21",
      "type": "OUTDOOR"
    },
    {
      "steps": 1096,
      "date": "2016-08-23",
      "type": "INDOOR"
    },
    {
      "steps": 482,
      "date": "2016-08-23",
      "type": "OUTDOOR"
    }
  ],
  "query": "monthly"
}
```

# device/getYearlyActivity
* Params(required) - macId
* For now, it only support currently year
* Return example:
```
{
  "success": true,
  "activity": [
    {
      "steps": 242,
      "date": "2016-08-21",
      "type": "INDOOR"
    },
    {
      "steps": 0,
      "date": "2016-08-21",
      "type": "OUTDOOR"
    },
    {
      "steps": 1096,
      "date": "2016-08-23",
      "type": "INDOOR"
    },
    {
      "steps": 482,
      "date": "2016-08-23",
      "type": "OUTDOOR"
    }
  ],
  "query": "monthly"
}
```

# user/updateIOSRegistrationId
* Params(required) - registrationId
* Example returns
```
  ['success':true]
```
* After updated the Registration ID, you can find it in
* www.childrenlab.com/user/getPushList


