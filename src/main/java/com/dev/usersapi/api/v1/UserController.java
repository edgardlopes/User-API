package com.dev.usersapi.api.v1;

import com.dev.usersapi.entity.Summary;
import com.dev.usersapi.entity.User;
import com.dev.usersapi.exception.ResourceNotFoundException;
import com.dev.usersapi.repository.UserRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public @ResponseBody
    Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    User getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public void create(@RequestBody @Valid User user, BindingResult result) throws MethodArgumentNotValidException {
        user.setId(null);

        if (repository.existsByCpf(user.getCpf())) {
            result.rejectValue("cpf", "field.already.exists", "Esse CPF já está em uso.");
        }

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        repository.save(user);
    }

    @PutMapping("/{id}")
    public void create(@PathVariable Long id, @Valid @RequestBody User user, BindingResult result) throws MethodArgumentNotValidException {
        user.setId(id);
        if (repository.existsByCpfAndIdNot(user.getCpf(), id)) {
            result.rejectValue("cpf", "field.already.exists", "Esse CPF já está em uso.");
        }

        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        repository.deleteById(id);
    }

    @GetMapping("/summary")
    public List<Summary> getSummary() {
        return repository.getSummary();
    }

}
