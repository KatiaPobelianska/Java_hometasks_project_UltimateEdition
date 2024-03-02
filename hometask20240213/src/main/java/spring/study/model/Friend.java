package spring.study.model;
/* Создайте приложение для хранения дней рождений Ваших друзей.
Приложение должно хранить имя (более одного символа) и фамилию (более одного символа) друга,
 а также его дату рождения (любая дата из прошлого, включая сегодняшнюю).
 Создайте метод для добавления записи о дне рождения, метод получения всех хранимых записей,
 метод получения сегодняшних именинников и методы поиска (именинники по дате, дата по имени
 и фамилии). Если при поиске данные не найдены, то должно быть брошено исключение,
 а контроллер должен обработать исключение и дать ответ 404.
Выполните валидацию данных при создании записи.
 В ответ на невалидные данные должен быть дан ответ с кодом 400.*/

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Friend {
    private long id;
    @NotBlank(message = "first name must be present")
    @Size(min = 2, max = 20, message = "first name size must be from 2 to 20 letters")
    private String firstName;
    @NotBlank(message = "last name must be present")
    @Size(min = 2, max = 20, message = "last name size must be from 2 to 20 letters")
    private String lastName;
    private LocalDate birthday;
}
