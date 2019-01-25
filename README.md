### Launch service
```bash
sbt run
```

### Add Post
`POST localhost:9000/posts`
```json
{
	"text": "post message here"
}
```

### Delete post
`DELETE localhost:9000/posts`
```json
{"id": "post uuid here"}
```

### Get posts
`GET localhost:9000/posts`

#### Response
```json
[
     {
       "id": "b3e95337-a715-4b6f-b150-2b76a798c895",
       "text": "some post",
       "count": 3
     },
     {
       "id": "b08731f2-43b0-4878-91e9-9f341eaa5cf6",
       "text": "another post",
       "count": 1
     },
     {
       "id": "a7356722-273a-48df-af62-7f5482824024",
       "text": "another another post",
       "count": -1
     }
   ]
```

### Add vote
`POST localhost:9000/vote`

Dir flags (up/down)
 `dir=1` or `dir=-1`
 
```json
{
	"postId": "b1a9c76d-8b55-4bc3-b17e-76097994eb1a",
	"dir": 1
}
```