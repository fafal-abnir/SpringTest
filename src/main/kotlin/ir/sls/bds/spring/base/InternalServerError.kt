package ir.sls.bds.spring.base

class InternalServerError : BadRequestException("Internal server error", 500, 1005)

class MediaUUIDIsNullException : BadRequestException("Media UUID should not be null", 500, 1006)
class CommentTextIsEmptyException : BadRequestException("Comment text should not be empty", 500, 1007)
class UserNotFoundException : BadRequestException("User not found", 404, 1008)
class CommentNotFoundException : BadRequestException("Comment not found", 404, 1009)
