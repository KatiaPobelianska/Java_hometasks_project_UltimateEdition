package spring.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.study.dao.ReviewDao;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewDao reviewDAO;

    @Autowired
    public ReviewController(ReviewDao reviewDAO) {
        this.reviewDAO = reviewDAO;
    }
    @GetMapping()
    public String reviewsPage(Model model){
        model.addAttribute("reviews", reviewDAO.getListOfAllReviews());
        return "all";
    }
}
