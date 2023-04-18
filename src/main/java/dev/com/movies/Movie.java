package dev.com.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies") //Đánh dấu là MongoDB document, xác định tên collection mà document này sẽ lưu trong MongoDB
@Data //Annotation của Lombok, tự động tạo các getter/setter method, toString method, equals method và hashCode method.
@AllArgsConstructor //Annotation của Lombok, tự động tạo một constructor với tất cả các tham số đầu vào.
@NoArgsConstructor // Annotation của Lombok, tự động tạo một constructor không tham số.
public class Movie {
    @Id
    private ObjectId id; // De biet day la Id va xem no la dinh danh cho moi Movie
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;

    @DocumentReference // Lưu tữ danh sách đối tượng Review, reviewIds sẽ lưu trữ các tham chiếu đến Review.
    private List<Review> reviewIds;

}
