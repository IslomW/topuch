## API Documentation

##Diagram
<img src="docs/diagram.png" width="800">

### Auth Controller
- **POST** `/api/v1/registration`: Register a new user.
- **POST** `/api/v1/authorization`: Authorize a user.

### Profile Controller
- **GET** `/api/v1/profile/{id}`: Retrieve a user profile by ID.
- **PUT** `/api/v1/profile/{id}`: Update a user profile.
- **GET** `/api/v1/profile`: Retrieve all user profiles (Admin Only).

### Movie Controller
- **GET** `/api/v1/movie/{id}`: Retrieve a movie by ID.
- **PUT** `/api/v1/movie/{id}`: Update a movie (Admin Only).
- **DELETE** `/api/v1/movie/{id}`: Delete a movie by ID (Admin Only).
- **GET** `/api/v1/movie`: Retrieve all movies.
- **POST** `/api/v1/movie`: Create a new movie (Admin Only).

### Reservation Controller
- **GET** `/api/v1/reservation`: Retrieve all reservations.
- **POST** `/api/v1/reservation`: Create a new reservation.
- **GET** `/api/v1/reservation/{id}`: Retrieve a reservation by ID.
- **DELETE** `/api/v1/reservation/{id}`: Delete a reservation by ID.

### Showtime Controller
- **GET** `/api/v1/showtime`: Retrieve all showtimes.
- **POST** `/api/v1/showtime`: Create a new showtime (Admin Only).
- **DELETE** `/api/v1/showtime/{id}`: Delete a showtime by ID (Admin Only).
- **GET** `/api/v1/showtime/{id}`: Retrieve a showtime by ID.

 
