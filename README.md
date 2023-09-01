# Simple Chat

- Мессенджер 

- Что делает ?

## Создание пользователей
- localhost:58087/chat/users/create
```json
{
  "name": "Aldi",
  "login": "aldi",
  "password": "123"
}
```


## Регистрация пользователей
- localhost:58082/chat/register
```json
{
  "login": "aldi",
  "password": "123"
}
```

## Создание ChatRoom (Group)
- localhost:58082/chat/chatRooms
```json
{
  "name": "teammates",
  "userIdList": [1, 2, 3]
}
```

## Отправка сообщения участнику группы (лс)
- localhost:58082/chat/messages/send
```json
{
  "fromUserId": 2,
  "toUserId": 3,
  "text": "Salam Arman2"
}
```

## Отправка сообщение в группу
- localhost:58082/chat/messages/send
```json
{
  "fromUserId": 1,
  "toChatId": 2,
  "text": "Salam pacanam ot Aldi"
}
```

