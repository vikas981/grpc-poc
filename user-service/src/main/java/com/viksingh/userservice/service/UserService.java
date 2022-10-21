package com.viksingh.userservice.service;


import com.viksingh.grpcpoc.common.Genre;
import com.viksingh.grpcpoc.user.UserGenreUpdateRequest;
import com.viksingh.grpcpoc.user.UserSearchRequest;
import com.viksingh.grpcpoc.user.UserSearchResponse;
import com.viksingh.grpcpoc.user.UserServiceGrpc;
import com.viksingh.userservice.entity.User;
import com.viksingh.userservice.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import java.util.Optional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserSearchResponse> responseObserver) {
        UserSearchResponse.Builder builder = UserSearchResponse.newBuilder();
        Optional<User> user = userRepository.findById(request.getLoginId());
        if(user.isPresent()){
           User u= user.get();
           builder.setName(u.getName())
                   .setLoginId(u.getLogin())
                   .setGenre(Genre.valueOf(u.getGenre().toUpperCase()));
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserSearchResponse> responseObserver) {
        UserSearchResponse.Builder builder = UserSearchResponse.newBuilder();
        Optional<User> user = userRepository.findById(request.getLoginId());
        if(user.isPresent()){
          User existingUser = user.get();
          User newUser = new User();
          newUser.setGenre(existingUser.getGenre());
          userRepository.save(newUser);
            builder.setName(existingUser.getName())
                    .setLoginId(newUser.getLogin())
                    .setGenre(Genre.valueOf(newUser.getGenre().toUpperCase()));
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
