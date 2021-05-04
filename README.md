# Record Your Workouts
#### Henry Larsen - CPSC 210 Personal Project

As someone who puts importance on physical health,
one of my greatest struggles had been organizing and
updating my workout routines. This project will be an
application to record, organize, manage, and update your
fitness programs, making working out easier.

This project will provide an easy way to:
 - *input* specific exercises (including repetitions, sets, weights,
  and descriptions)
 - *organize* workouts into categories
 - *modify* past workouts to keep up with your physical gains
 - *alter* workouts to be circuits, body weight or timed

## User Stories (Phase 1)
- *As a user*, I want to create a workout and add exercises to it
- *As a user*, I want to select the sets, repetitions, and weights
of each exercise
- *As a user*, I want to be able to view a list of my workouts
- *As a user*, I want to be able to view all the exercises in a
specific workout

## User Stories (Phase 2)
- *As a user*, I want to be able to save my to-do list to file
- *As a user*, I want to be able to save my to-do list from file

## Phase 4: Task 2
I have chosen to implement the second option, including a type hierarchy
other than the one using the Saveable interface. This is implemented in
the tools package. The abstract Tool class extends all other
classes in the package. In each class extending Tool (such as SaveTool
or ViewRoutineTool), the methods for createFields and addListener are overridden
differently to provide each subclass with unique functionality. Tool is the 
abstract class. AddExerciseTool, AudioVisualTool, CreateWorkoutTool, LoadTool,
SaveTool, ViewRoutineTool, and ViewWorkoutsTool are all of its subclasses.

## Phase 4: Task 3
Looking at my UMI diagram there are a few things that I think I would like to refactor.
First, there is too much coupling between LoadTool and WorkoutListGUI and the Tools. I don't 
think it is necessary for WorkoutListGUI to be associated with each tool. Secondly, there is
probably a way to eliminate the reliance of LoadTool on WorkoutListGUI by using the fields of
abstract Tool class instead.