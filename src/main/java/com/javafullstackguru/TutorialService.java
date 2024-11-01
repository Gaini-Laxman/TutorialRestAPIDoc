package com.javafullstackguru;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javafullstackguru.entity.Tutorial;
import com.javafullstackguru.repository.TutorialRepository;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials(String title) {
        if (title == null) {
            return tutorialRepository.findAll();
        } else {
            return tutorialRepository.findByTitleContaining(title);
        }
    }

    public Optional<Tutorial> getTutorialById(long id) {
        return tutorialRepository.findById(id);
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Optional<Tutorial> updateTutorial(long id, Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial updatedTutorial = tutorialData.get();
            updatedTutorial.setTitle(tutorial.getTitle());
            updatedTutorial.setDescription(tutorial.getDescription());
            updatedTutorial.setPublished(tutorial.isPublished());
            return Optional.of(tutorialRepository.save(updatedTutorial));
        }
        return Optional.empty();
    }

    public boolean deleteTutorial(long id) {
        try {
            tutorialRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findByPublished() {
        return tutorialRepository.findByPublished(true);
    }
}

