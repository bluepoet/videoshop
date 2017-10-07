package kr.bluepoet.videoshop.domain;

/**
 * Created by bluepoet on 2017. 10. 7..
 */
public interface RentRepository {
    Rent findById(long id);
}
