package com.upbeat.upbeat.domain.cardgame.service;

import com.upbeat.upbeat.domain.cardgame.dto.request.LikeRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.LikeResponseDto;
import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import com.upbeat.upbeat.domain.cardgame.entity.Like;
import com.upbeat.upbeat.domain.cardgame.repository.AnswerRepository;
import com.upbeat.upbeat.domain.cardgame.repository.LikeRepository;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public LikeResponseDto create(LikeRequestDto dto,Long userId){
        Answer answer = answerRepository.findById(dto.getAnswerId()).orElseThrow(()->new IllegalArgumentException("좋아요 실패."+"대상 답변이 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        boolean alreadyLiked = likeRepository.existsByUserIdAndAnswerId(userId, dto.getAnswerId());
        if (alreadyLiked) {
            throw new IllegalStateException("이미 좋아요를 누르셨습니다.");
        }

        Like like = Like.create(dto,user,answer);
        likeRepository.save(like);

        int likeCount = likeRepository.countByAnswerId(dto.getAnswerId());
        answer.setLikes(likeCount);
        answerRepository.save(answer);

        return LikeResponseDto.createLikeDto(like,likeCount);
    }

    public LikeResponseDto delete(Long answerId,Long userId){
        Like deleted = likeRepository.findByAnswerIdAndUserId(answerId,userId).orElse(null);
        if(deleted==null){
            return null;
        }
        likeRepository.delete(deleted);

        Answer target = answerRepository.findById(answerId).orElseThrow();
        int likeCount = likeRepository.countByAnswerId(answerId);
        target.setLikes(likeCount);
        answerRepository.save(target);

        return LikeResponseDto.createLikeDto(deleted,likeCount);
    }
}
