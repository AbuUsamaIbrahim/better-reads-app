package io.javabrains.betterReads.userBook;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserBooksRepository extends CassandraRepository<UserBooks,UserBooksPrimaryKey> {
  
}