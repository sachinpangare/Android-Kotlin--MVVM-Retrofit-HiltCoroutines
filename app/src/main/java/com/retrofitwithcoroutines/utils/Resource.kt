package com.retrofitwithcoroutines.utils

import com.retrofitwithcoroutines.data.model.User

class Resource<out T>(val status:Status,val data:T?,val message:String?) : List<User> {

    companion object{

        fun <T> success(data:T):Resource<T> = Resource(status = Status.SUCCESS,data = data,message = null)

        fun <T> loading(data:T?):Resource<T> = Resource(status = Status.LOADING,data=data,message = null)

        fun <T> error(data: T?, message: String): Resource<T> = Resource(status = Status.ERROR, data = data, message = message)
    }

    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<User>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): User {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: User): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<User> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: User): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<User> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<User> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<User> {
        TODO("Not yet implemented")
    }
}
