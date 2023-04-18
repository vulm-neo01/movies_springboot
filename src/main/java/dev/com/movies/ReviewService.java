package dev.com.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        //Phương thức update() trả về một đối tượng Update mà chúng ta có thể sử dụng để chỉ định các trường cần cập nhật trên document.
        // Phương thức matching() được sử dụng để chỉ định điều kiện để xác định document cần cập nhật. Ở đây, chúng ta sử dụng Criteria.where("imdbId").is(imdbId)) để chỉ định điều kiện là trường "imdbId" phải bằng giá trị của biến imdbId.
        // Phương thức apply() được sử dụng để chỉ định cách cập nhật document. Ở đây, chúng ta sử dụng new Update().push("reviewIds").value(review) để cập nhật trường "reviewIds" bằng cách thêm giá trị của biến review vào mảng reviewIds của document.
        //method first() để chỉ định rằng chỉ cập nhật tài liệu đầu tiên phù hợp với tiêu chí tìm kiếm, nếu không tìm thấy tài liệu phù hợp thì không thực hiện bất kỳ thay đổi nào.
        return review;
    }
}
