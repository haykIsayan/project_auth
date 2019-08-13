package com.example.project_auth

import com.example.project_auth.model.Resource
import com.example.project_auth.model.User

class NoUserFoundResource: Resource.FailResource<User>(null)

class PasswordIncorrectResource: Resource.FailResource<User>(null)

class AlreadyRegisteredResource: Resource.FailResource<User>(null)

class UserListEmptyResource: Resource.FailResource<List<User>>(null)