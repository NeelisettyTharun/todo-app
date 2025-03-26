import React, { useState, useEffect } from "react";
import axios from "axios";

const App = () => {
  const [todos, setTodos] = useState([]);
  const [task, setTask] = useState("");

  // Fetch Todos on Component Mount
  useEffect(() => {
    fetchTodos();
  }, []);

  // Fetch All Todos
  const fetchTodos = async () => {
    try {
      const response = await axios.get("http://localhost:9090/todos");
      setTodos(response.data);
    } catch (error) {
      console.error("Error fetching todos:", error);
    }
  };

  // Add a New Todo
  const addTodo = async () => {
    if (!task.trim()) return;
    try {
      await axios.post("http://localhost:9090/todos", { task, completed: false });
      setTask(""); // Clear input field after adding
      fetchTodos(); // Refresh the list
    } catch (error) {
      console.error("Error adding todo:", error);
    }
  };

  // Delete a Todo
  const deleteTodo = async (id) => {
    console.log("Trying to delete todo with ID:", id); // Debugging Log
    try {
      const response = await axios.delete(`http://localhost:9090/todos/${id}`);
      console.log("Delete response:", response.status); // Log response status
      fetchTodos(); // Refresh the list after deletion
    } catch (error) {
      console.error("Error deleting todo:", error.response?.data || error.message);
    }
  };
  
  return (
    <div style={{ textAlign: "center", marginTop: "20px" }}>
      <h1>To-Do List</h1>
      <input
        type="text"
        value={task}
        onChange={(e) => setTask(e.target.value)}
        placeholder="Add a new task"
      />
      <button onClick={addTodo}>Add</button>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.task} (ID: {todo.id}) {/* Displaying ID for debugging */}
            <button onClick={() => deleteTodo(todo.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default App;
