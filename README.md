# childrenLab


# /childrenLab/api/login
* Params - email, password
* Return example: {
"username": "test@test.com",
"roles": [
"ROLE_USER"
],
"access_token": "4ncvvg81j5smer66avrgdasgvcncngd3",
"token_type": "Bearer"
}

* Exception: if email or password not match, the response status will be 401 Unauthorized (let me know if you don't like it...)

# /childrenLab/user/register
* Params(required) - email, password, phoneNumber, firstName, lastName
* other Params - birthday, nickName, sex, address, city, zipCode, role(2 type: ROLE_USER, ROLE_NANNY)
* The default role is ROLE_USER

* Exception example: {
"success": false,
"message": "The email already registered."
}
