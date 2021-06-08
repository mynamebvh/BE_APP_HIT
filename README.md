## ⭐ GETTING STARTED
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fmynamebvh%2FBE_APP_HIT.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fmynamebvh%2FBE_APP_HIT?ref=badge_shield)


- [Introduction](#🏷-introduction)
- [Team Member](#team-member)
- [API Docs](#api-docs)

  - API ADMIN
    - API User
    - API ClassRoom
  - API Member
  - API Global
    - API Authentication
      - [API Login](#api-login)
      - [API Signup](#api-signup)
    - API POST
    - API COMMENT

# INTRODUCTION

1 sản phẩm của nhóm giúp quản lí clb HIT - ĐHCNHN

# TEAM MEMBER

- Backend: [Bùi Việt Hoàng](https://www.facebook.com/MyNameBVH/)
- Android: [Cao Đắc Thuận](https://www.facebook.com/100009048064612/) x [Mai Hường](https://www.facebook.com/100010444626797/)

# API DOCS

### API Authentication

#### API Login

```
POST: localhost:8080/api/v1/auth/login
```

**Body: (Type Json)**

```
{

    "username": "2019606167",
    "password": "Hoangdz23@"
}
```

**Validate:**

```
username: bắt buộc là số và có 10 kí tự
password: Độ dài tối thiểu 8 kí tự, có ít nhất 1 chữ Hoa, 1 chữ thường, 1 kí tự đặc biệt
```

**Response:**

```
{
    "statusCode": 200,
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDE5NjA2MTY3IiwiZXhwIjoxNjIxMjk5Mzc3LCJpYXQiOjE2MjEyNjMzNzd9.MBNQ_JZomk9GMBXDkxbew_l8vUvveWcX2a4hunxQgSE",
    "userId": 1,
    "username": "2019606167",
    "role": "MEMBER"
}
```

#### API Signup

```
POST localhost:8080/api/v1/auth/signup
```

**Body: (Type Json)**

```
{
    "fullname": "Hoang Bui",
    "userName": "2019606127",
    "password": "Hoangdz23@",
    "email": "mynamebvh@gmail.com",
    "phone": "0979150931",
    "birthday": "23/12/2001"
}
```

**Validate:**

```
username: bắt buộc là số và có 10 kí tự
password: Độ dài tối thiểu 8 kí tự, có ít nhất 1 chữ Hoa, 1 chữ thường, 1 kí tự đặc biệt
email: đúng định dạng
phone: đúng định dạng
birthday: đang fix :v
```

**Response:**

```
Update
```


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fmynamebvh%2FBE_APP_HIT.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fmynamebvh%2FBE_APP_HIT?ref=badge_large)