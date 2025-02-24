package program_classes.Model

interface IListUpdateListener<T> {
    fun notifyListUpdate(newList: List<T>)
}