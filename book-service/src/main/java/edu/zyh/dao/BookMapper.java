package edu.zyh.dao;

import edu.zyh.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    @Select("select * from book")
    List<Book> getAllBook();

    @Select("select * from book where id = #{id}")
    Book findBookById(int id);

    @Select("select price from book where id = #{id}")
    double getBookPriceById(int id);

    @Select("select *from (select all_info.id from (select id, concat(book_name, author) as info from book ) all_info " +
            "where all_info.info like #{bookName} and all_info.info like #{author}" +
            "     ) as result join book on result.id where result.id = book.id")
    List<Book> getBookByBookNameOrAuthor(@Param("bookName") String bookName, @Param("author") String author);

    @Insert("insert into book(book_name, price, author, cover_img, summary) VALUE (#{newBook.bookName}, #{newBook.price}," +
            "#{newBook.author}, #{newBook.coverImg}, #{newBook.summary})")
    int insertNewBook(@Param("newBook") Book newBook);

    @Delete("delete from book where id = #{bookId}")
    int delBook(@Param("bookId") int bookId);
}
