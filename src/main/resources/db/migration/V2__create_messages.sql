create sequence message_sequence start with 1 increment by 1;
create sequence chat_room_sequence start with 1 increment by 1;

create table chat_room (
    id BIGINT primary key,
    name varchar(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE user_chatroom (
    user_id BIGINT REFERENCES users(id),
    chatroom_id BIGINT REFERENCES chat_room(id),
    PRIMARY KEY (user_id, chatroom_id)
);

create table messages(
    id BIGINT primary key,
    sender_id BIGINT references users(id),
    chatroom_id BIGINT references chat_room(id),
    message_content text,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);