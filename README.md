# DDD Trilemma Solution

This repository contains a possible solution of the [DDD trilemma presented by Vladimir Khorikov](https://enterprisecraftsmanship.com/posts/domain-model-purity-completeness/) and it accompanies my YouTube video https://youtu.be/_8MBzfSkVtA 

## Explanation

This is an application following the Model-View-Controller (MVC) architecture. Only two layers are implemented: Controllers and (Domain) Model. We are assuming the View is handled elsewhere.

To keep the model **complete**, we enclosed all business logic in the [`UserService`](src/main/java/eu/marcolenzo/videodddtrilemma/model/services/UserService.java) and [`User`](src/main/java/eu/marcolenzo/videodddtrilemma/model/entities/User.java) classes which are both part of the domain.

To keep the model **pure**, we moved the out-of-process dependencies to the [`UserService`](src/main/java/eu/marcolenzo/videodddtrilemma/model/services/UserService.java) class.

As you can see, the concept of purity is a bit skewed. If we apply it strictly to the entire domain layer, this model is not pure. However, if we apply the concept of purity only to object holding state, such as `Entities` and `Value Objects`, this model is pure!

Keep in mind that the `Repository` is another building block in Tactical Domain-Driven-Design (DDD). Thus, it is an integral part of the model itself.

To sum up, we reached a different conclusion than the one suggested by Vladimir. In our scenario, **we'd rather sacrifice purity** rather than completeness because we believe spreading the core business logic across layers is a bigger evil.