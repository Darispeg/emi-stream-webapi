package com.dragocxz.emistream.mapper;

import com.dragocxz.emistream.dto.course.CourseRequest;
import com.dragocxz.emistream.dto.course.ThumbnailCourseRequest;
import com.dragocxz.emistream.dto.course.TopicCourseRequest;
import com.dragocxz.emistream.model.Course;
import com.dragocxz.emistream.model.course.ThumbnailCourse;
import com.dragocxz.emistream.model.course.TopicCourse;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CourseMapper {

    public Course mapToCourse(CourseRequest source) {
        if (source == null) {
            return null;
        }

        Course course = new Course();
        course.setTitle(source.getTitle());
        course.setDescription(source.getDescription());
        course.setBenefits(source.getBenefits());
        course.setRequirements(source.getRequirements());
        course.setLevel(source.getLevel());
        course.setDuration(source.getDuration());
        course.setCategory(source.getCategory());
        course.setCourseStatus(source.getCourseStatus());
        course.setCertification(source.isCertification());

        // Mapeo de arrays de objetos (topics y thumbnail)
        if (source.getTopics() != null) {
            TopicCourse[] topicCourses = Arrays.stream(source.getTopics())
                    .map(CourseMapper::mapToTopicCourse)
                    .toArray(TopicCourse[]::new);
            course.setTopics(topicCourses);
        }

        if (source.getThumbnail() != null) {
            course.setThumbnail(mapToThumbnailCourse(source.getThumbnail()));
        }

        return course;
    }

    // Método de mapeo para TopicCourseRequest a TopicCourse
    private static TopicCourse mapToTopicCourse(TopicCourseRequest request) {
        if (request == null) {
            return null;
        }
        TopicCourse topicCourse = new TopicCourse();

        topicCourse.setId(request.getId());
        topicCourse.setName(request.getName());
        topicCourse.setDescription(request.getDescription());
        topicCourse.setStatus(request.getStatus());
        topicCourse.setUrl(request.getUrl());

        return topicCourse;
    }

    // Método de mapeo para ThumbnailCourseRequest a ThumbnailCourse
    private static ThumbnailCourse mapToThumbnailCourse(ThumbnailCourseRequest request) {
        if (request == null) {
            return null;
        }

        ThumbnailCourse thumbnailCourse = new ThumbnailCourse();
        thumbnailCourse.setId(request.getId());
        thumbnailCourse.setVideoId(request.getVideoId());
        thumbnailCourse.setVideoUrl(request.getVideoUrl());

        return thumbnailCourse;
    }

    public void updateCourseFromRequest(Course existingCourse, CourseRequest request) {
        if (existingCourse == null || request == null) {
            return;
        }

        // Actualizar solo los campos que se proporcionan en la solicitud
        if (request.getTitle() != null) {
            existingCourse.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            existingCourse.setDescription(request.getDescription());
        }
        if (request.getBenefits() != null) {
            existingCourse.setBenefits(request.getBenefits());
        }
        if (request.getRequirements() != null) {
            existingCourse.setRequirements(request.getRequirements());
        }
        if (request.getLevel() != null) {
            existingCourse.setLevel(request.getLevel());
        }
        if (request.getDuration() != null) {
            existingCourse.setDuration(request.getDuration());
        }
        if (request.getCategory() != null) {
            existingCourse.setCategory(request.getCategory());
        }
        if (request.getCourseStatus() != null) {
            existingCourse.setCourseStatus(request.getCourseStatus());
        }
        // Otros campos que desees actualizar según la lógica de tu aplicación

        // Actualizar el arreglo de topics si se proporciona en la solicitud
        if (request.getTopics() != null && request.getTopics().length > 0) {
            // Borra los topics existentes y asigna los nuevos topics desde la solicitud
            existingCourse.setTopics(mapToTopicCourses(request.getTopics()));
        }

        // Actualizar el thumbnail si se proporciona en la solicitud
        if (request.getThumbnail() != null) {
            existingCourse.setThumbnail(mapToThumbnailCourse(request.getThumbnail()));
        }
    }

    private TopicCourse[] mapToTopicCourses(TopicCourseRequest[] topicRequests) {
        if (topicRequests == null) {
            return new TopicCourse[0];
        }

        return Arrays.stream(topicRequests)
                .map(this::mapUpdatedToTopicCourse)
                .toArray(TopicCourse[]::new);
    }

    private TopicCourse mapUpdatedToTopicCourse(TopicCourseRequest request) {
        if (request == null) {
            return null;
        }
        TopicCourse topicCourse = new TopicCourse();

        topicCourse.setId(request.getId());
        topicCourse.setName(request.getName());
        topicCourse.setDescription(request.getDescription());
        topicCourse.setStatus(request.getStatus());
        topicCourse.setUrl(request.getUrl());

        return topicCourse;
    }
}
