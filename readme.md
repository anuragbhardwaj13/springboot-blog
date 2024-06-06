# Setting up Environment Locally

Add following as Program Arguments / Environment Secrets
```
--spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.dozbqne.mongodb.net/ --blogapp.jwt.jwtSecret=<jwt_secret> --blogapp.jwt.jwtExpirationMs=86400000
```

# User API Endpoints

## HealthCheck

**Endpoint:** `GET /public/health`

**Description:** Check the health status of the service.

**Authorization:** No authentication required

**Request:**
```
GET http://34.93.207.250/api/public/health
```

---

## Signup

**Endpoint:** `POST /api/public/signup`

**Description:** Register a new user.

**Authorization:** No authentication required

**Request:**
```
POST http://34.93.207.250/api/public/signup
Content-Type: application/json

{
    "username": "string",
    "email": "string",
    "password": "string",
    "role": ["user" | "mod" ]
}
```



---

## Signin

**Endpoint:** `POST /api/public/signin`

**Description:** Authenticate a user and obtain a accessToken.

**Authorization:** No authentication required

**Request:**
```
POST http://34.93.207.250/api/public/signin
Content-Type: application/json

{
    "identifier": "string",
    "password": "string"
}

```
## dummy credentials for moderator
```
{
    "identifier":"mod-test",
    "password":"mod-test"
}

```
---

# Posts API Endpoints

## Get Posts

**Endpoint:** `GET /api/post`

**Description:** Retrieve all approved posts.

**Authorization:** Bearer token required

**Request:**
```
GET http://34.93.207.250/api/post
Authorization: Bearer <your_token>
```

---

## Create New Post

**Endpoint:** `POST /api/post`

**Description:** Create a new post.

**Authorization:** Bearer token required

**Request:**
```
POST http://34.93.207.250/api/post
Authorization: Bearer <your_token>
Content-Type: application/json

{
    "title": "string",
    "content": "string"
}
```



---

## Get All Posts by (Admin/Mod Only)

**Endpoint:** `GET /api/post/all`

**Description:** Retrieve all posts, visible to admin and moderators.

**Authorization:** Bearer token required

**Request:**
```
GET http://34.93.207.250/api/post/all
Authorization: Bearer <your_token>
```



---

## Approve Post (Admin/Mod Only)

**Endpoint:** `POST /api/post/approve/{id}`

**Description:** Approve a post, changing its status to "APPROVED".

**Authorization:** Bearer token required

**Request:**
```
POST http://34.93.207.250/api/post/approve/{id}
Authorization: Bearer <your_token>
```

