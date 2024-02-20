package org.example.controller;

import org.example.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* Дополните проект, который мы использовали на лекции для работы с Users.
1.1 Добавьте в сущность User поле status. Создайте форму для установки статуса.
Форма должна использовать метод PATCH. После обновления статуса пользователя происходит
 перенаправление на страницу его профиля,где должен отображаться также статус.
1.2 Напишите перехватчик, который логирует детали всех входящих
HTTP запросов и ответов на них (URL, параметры, тело, заголовки и т.д.).
1.3 Используя Session scope бин, разработайте механизм,
который автоматически определяет, новый пользователь это или возвращающийся,
и выводит соответствующее приветствие на главной странице сайта – «Welcome,
new user!» или «Welcome back!». */
@Controller
public class HomeController {
    private final UserSession userSession;

    @Autowired
    public HomeController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String home(Model model) {
        if (userSession.isNewUser()) {
            model.addAttribute("message", "Welcome, new user!");
        } else {
            model.addAttribute("message", "Welcome back!");
        }
        userSession.setNewUser(false);
        return "home";
    }
}
