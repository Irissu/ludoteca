package com.ccsw.tutorial.game;

import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public List<Game> find(String title, Long idCategory) {
        //        falta implementación para filtrar game por categoria y titulo
        return (List<Game>) this.gameRepository.findAll();
    }

    @Override
    public void save(Long id, GameDto dto) {
        Game game;

        if (id == null) {
            game = new Game();
        } else {
            game = this.gameRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, game, "id", "author", "category"); // primer parametro, objeto fuente, segundo objeto destino, tercero parametros ignorados

        this.gameRepository.save(game);

    }
}
