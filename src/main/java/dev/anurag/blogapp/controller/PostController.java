        package dev.anurag.blogapp.controller;

        import dev.anurag.blogapp.dto.MessageResponse;
        import dev.anurag.blogapp.entity.BlogPost;
        import dev.anurag.blogapp.entity.User;
        import dev.anurag.blogapp.repository.BlogPostRepository;
        import dev.anurag.blogapp.repository.UserRepository;
        import dev.anurag.blogapp.secuity.service.UserDetailsImpl;
        import dev.anurag.blogapp.service.PostService;
        import dev.anurag.blogapp.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.web.bind.annotation.*;

        import java.net.http.HttpResponse;
        import java.util.List;
        import java.util.stream.Collectors;

        @RestController
        @RequestMapping("/api/post")
        public class PostController {
            @Autowired
            AuthenticationManager authenticationManager;
            @Autowired
            private UserRepository userRepository;
            @Autowired
            private PostService postService;
            @Autowired
            private UserService userService;
            @PostMapping
            public ResponseEntity<?> createEntry(@RequestBody BlogPost entry) {
                try {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                    String username=userDetails.getUsername();
                    postService.saveEntry(entry, username);
                    return new ResponseEntity<>(new MessageResponse("Post Created"),HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            }
            @PutMapping("/id/{postId}")
            public ResponseEntity<?> updateEntry(@RequestBody BlogPost updatedPost, @PathVariable long postId) {
                try {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                    String username=userDetails.getUsername();
                    User user=userService.findUserByUsername(username);
                    List<BlogPost> collection=user.getBlogPosts().stream().filter(x->x.getId()==postId).collect(Collectors.toList());
                    if(!collection.isEmpty()){
                        BlogPost oldPost=postService.getById(postId);
                        oldPost.setTitle(updatedPost.getTitle() != null && !updatedPost.getTitle().isEmpty() ? updatedPost.getTitle() : oldPost.getTitle());
                        oldPost.setContent(updatedPost.getContent() != null && !updatedPost.getContent().isEmpty() ? updatedPost.getContent() : oldPost.getContent());
                        postService.updateEntry(oldPost,username);
                        return new ResponseEntity<>(oldPost, HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            }
            @PostMapping("/approve/{postId}")
            @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
            public ResponseEntity<?> approvePost(@PathVariable String postId) {
                BlogPost approvedPost = postService.approvePost(postId);
                return new ResponseEntity<>(approvedPost, HttpStatus.OK);
            }

            @GetMapping
            public ResponseEntity<?> getAllApprovedPostsForUser() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                String username = userDetails.getUsername();

                List<BlogPost> allEntries = postService.findAllApprovedByUserUsername(username);
                if (allEntries != null && !allEntries.isEmpty()) {
                    return new ResponseEntity<>(allEntries, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            @GetMapping("/all")
            @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
            public ResponseEntity<?> getAllPostsForUser() {

                List<BlogPost> allEntries = postService.getAllEntries();
                if (allEntries != null && !allEntries.isEmpty()) {
                    return new ResponseEntity<>(allEntries, HttpStatus.OK);
                }
                return new ResponseEntity<>(allEntries, HttpStatus.NOT_FOUND);
            }


        }
