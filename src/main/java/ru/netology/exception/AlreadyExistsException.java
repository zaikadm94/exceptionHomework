package ru.netology.exception;


public class AlreadyExistsException extends RuntimeException { // создал класс AlreadyExistsException наследующийся от предка
    public AlreadyExistsException(String message) { // создал конструктор с параметром сообщение
        super(message); //...который вызывает конструктор предка
    }
}
