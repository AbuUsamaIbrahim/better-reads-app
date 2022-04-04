package io.javabrains.betterReads.userBook;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table(value = "book_by_user_and_bookId")
public class UserBooks {

  @PrimaryKey
  UserBooksPrimaryKey key;

  public UserBooksPrimaryKey getKey() {
    return key;
  }

  public void setKey(UserBooksPrimaryKey key) {
    this.key = key;
  }

  @Column("reading_status")
  @CassandraType(type = Name.TEXT)
  private String readingStatus;

  @Column("rating")
  @CassandraType(type = Name.INT)
  private int ratring;

  public int getRatring() {
    return ratring;
  }

  public void setRatring(int ratring) {
    this.ratring = ratring;
  }

  @Column("start_date")
  @CassandraType(type = Name.DATE)
  private LocalDate startDate;

  public String getReadingStatus() {
    return readingStatus;
  }

  public void setReadingStatus(String readingStatus) {
    this.readingStatus = readingStatus;
  }



  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Column("end_date")
  @CassandraType(type = Name.DATE)
  private LocalDate endDate;


}
