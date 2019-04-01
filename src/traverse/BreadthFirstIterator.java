package traverse;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class BreadthFirstIterator
    extends CrossIterator<BreadthFirstIterator.SearchNodeData<StepInterface>> {

  private Deque<StepInterface> queue = new ArrayDeque<>();

  /**
   * Creates a new breadth-first iterator for the specified Explorable. Iteration will start at the
   * specified start step.
   *
   * @param explorable the data structure to be iterated.
   * @param startStep the step iteration to be started.
   */
  public BreadthFirstIterator(Explorable explorable, StepInterface startStep) {
    super(explorable, startStep);
  }

  public BreadthFirstIterator(
      Explorable explorable, StepInterface startStep, StepInterface endStep) {
    super(explorable, startStep, endStep);
  }

  /** {@inheritDoc} */
  protected StepInterface nextStep() {
    return queue.removeFirst();
  }

  protected boolean isConnectedComponentExhausted() {
    return queue.isEmpty();
  }

  protected void encounterStep(StepInterface step, StepInterface fromStep) {
    Objects.requireNonNull(step);
    // when fromStep == null, it's the start step , so I have a depth of 0.
    int depth = (fromStep == null ? 0 : getSeenData(fromStep).depth + 1);

    putSeenData(step, new SearchNodeData<>(fromStep, depth));
    queue.add(step);
  }

  /** {@inheritDoc} */
  protected void encounterStepAgain(StepInterface step, StepInterface stepFrom) {}

  /**
   * Returns the depth of step in the data structure. The depth of a step is defined as the number
   * of steps traversed on the path from the beginning (first step) to step. The beginning has depth
   * 0. This method can only be invoked on a step once the iterator has visited step!
   *
   * @param step step
   * @return depth of step in the data structure.
   */
  public int getDepth(StepInterface step) {
    assert getSeenData(step) != null;
    return getSeenData(step).depth;
  }

  /**
   * Little class to encapsulate data about StepInterface that's been traversed.
   *
   * @param <S> the step type
   */
  static class SearchNodeData<S> {
    final S step;

    /** Depth of node in search tree */
    final int depth;

    SearchNodeData(S step, int depth) {
      this.step = step;
      this.depth = depth;
    }
  }
}
