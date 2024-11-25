package com.life_manager.life_manager.user.infra.S3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String BUCKET_NAME;

    @SneakyThrows
    public String uploadProfileImage(String workerId, MultipartFile profileImage) {
        // 이미지 파일인지 검증
        if (!profileImage.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .key(workerId)
                .bucket(BUCKET_NAME)
                .contentType(profileImage.getContentType())
                .contentLength(profileImage.getSize())
                .build();

        RequestBody requestBody = RequestBody.fromInputStream(profileImage.getInputStream(), profileImage.getSize());

        PutObjectResponse response = s3Client.putObject(putObjectRequest, requestBody);

        if (!response.sdkHttpResponse().isSuccessful()) {
            log.error("S3 업로드 실패: {}", response.sdkHttpResponse().statusText());
        }

        // 업로드 경로 반환
        return s3Client.utilities().getUrl(builder -> builder.bucket(BUCKET_NAME).key(workerId)).toExternalForm();
    }


    @SneakyThrows
    public String uploadImage(String adminId, MultipartFile profileImage) {
        if (!profileImage.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        String uniqueKey = UUID.randomUUID().toString();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .key(uniqueKey)
                .bucket(BUCKET_NAME)
                .contentType(profileImage.getContentType())
                .contentLength(profileImage.getSize())
                .build();

        RequestBody requestBody = RequestBody.fromInputStream(profileImage.getInputStream(), profileImage.getSize());

        PutObjectResponse response = s3Client.putObject(putObjectRequest, requestBody);

        if (!response.sdkHttpResponse().isSuccessful()) {
            log.error("S3 업로드 실패: {}", response.sdkHttpResponse().statusText());
        }

        // 업로드 경로 반환
        return s3Client.utilities().getUrl(builder -> builder.bucket(BUCKET_NAME).key(adminId)).toExternalForm();
    }

    @SneakyThrows
    public List<String> uploadImageList(String adminId, List<MultipartFile> profileImageList) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile profileImage : profileImageList) {
            if (!profileImage.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
            }

            String uniqueKey = UUID.randomUUID().toString();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .key(uniqueKey)
                    .bucket(BUCKET_NAME)
                    .contentType(profileImage.getContentType())
                    .contentLength(profileImage.getSize())
                    .build();

            RequestBody requestBody = RequestBody.fromInputStream(profileImage.getInputStream(), profileImage.getSize());

            PutObjectResponse response = s3Client.putObject(putObjectRequest, requestBody);

            if (!response.sdkHttpResponse().isSuccessful()) {
                log.error("S3 업로드 실패: {}", response.sdkHttpResponse().statusText());
                throw new RuntimeException("S3 업로드 실패: " + response.sdkHttpResponse().statusText());
            }

            String imageUrl = s3Client.utilities().getUrl(builder -> builder.bucket(BUCKET_NAME).key(uniqueKey)).toExternalForm();
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }
}
