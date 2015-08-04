package remixlab.bias.addon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import remixlab.bias.core.Agent;
import remixlab.bias.core.BogusEvent;
import remixlab.bias.core.EventGrabberTuple;
import remixlab.bias.core.Grabber;
import remixlab.bias.core.InputHandler;
import remixlab.bias.event.MotionEvent;

/**
 * A {@link remixlab.bias.core.Agent} extended with branches, see
 * {@link remixlab.bias.addon.MotionBranchAgent#appendBranch(String)},
 * {@link remixlab.bias.addon.KeyboardBranchAgent#appendBranch(String)}.
 * <p>
 * For branch handling refer to methods such
 * as {@link #pruneBranch(Branch)}, {@link #branches()}, {@link #branch(Grabber)} and others. Branches enable the agent
 * to parse bogus-events into {@link remixlab.bias.addon.InteractiveGrabber} object {@link remixlab.bias.addon.Action}s
 * (see {@link #addGrabber(InteractiveGrabber, Branch)}). Please refer to the {@link remixlab.bias.addon.Branch} and the
 * {@link remixlab.bias.addon.InteractiveGrabber} documentations for details.
 */
public abstract class BranchAgent extends Agent {
	protected List<Branch<?>>		brnchs;
	protected Branch<?>				trackedGrabberBranch, defaultGrabberBranch;

	/**
	 * Constructs a BranchAgent with the given name and registers is at the given inputHandler.
	 */
	public BranchAgent(InputHandler inputHandler, String name) {
		super(inputHandler, name);
		brnchs = new ArrayList<Branch<?>>();
	}
	
	// 1. Grabbers

	@Override
	public boolean removeGrabber(Grabber grabber) {
		if( !super.removeGrabber(grabber) )
			for (int i = 0; i < brnchs.size(); i++)
				if(brnchs.get(i).removeGrabber(grabber))
					return true;
		return false;
	}
	
	@Override
	public void removeGrabbers() {
		super.removeGrabbers();		
		for (Iterator<Branch<?>> it = brnchs.iterator(); it.hasNext();)
			it.next().reset();
	}
	
	@Override
	public List<Grabber> grabbers() {
		List<Grabber> pool = super.grabbers();		
		for (int i = 0; i < brnchs.size(); i++) {
			pool.removeAll(brnchs.get(i).grabbers());
			pool.addAll(brnchs.get(i).grabbers());
		}		
		return pool;
	}

	@Override
	public boolean addGrabber(Grabber grabber) {
		if( !super.addGrabber(grabber) )
			if (grabber instanceof InteractiveGrabber)
				System.err.println("use addGrabber(G grabber, K Branch) instead");
		return false;
	}
	
	/**
	 * Returns the branch list of interactive-grabber objects.
	 * 
	 * @see #addGrabber(InteractiveGrabber, Branch)
	 * @see #removeGrabber(Grabber)
	 * @see #addGrabber(Grabber)
	 * @see #hasGrabber(Grabber)
	 * @see #removeGrabbers()
	 * @see #grabbers()
	 */
	public <E extends Enum<E>> List<InteractiveGrabber<E>> grabbers(Branch<E> branch) {
		return branch.grabbers();
	}
	
	/**
	 * Adds grabber to branch. 
	 *
	 * @see #removeGrabber(Grabber)
	 * @see #addGrabber(Grabber)
	 * @see #hasGrabber(Grabber)
	 * @see #removeGrabbers()
	 * @see #grabbers()
	 * @see #grabbers(Branch)
	 */
	public <E extends Enum<E>, K extends Branch<E>, G extends InteractiveGrabber<E>> boolean addGrabber(G grabber, K branch) {
		if(branch == null)
			return false;
		if (!hasBranch(branch)) {
			if (!this.appendBranch(branch))
				return false;
			return false;
		}
		return branch.addGrabber(grabber);
	}
	
	// 2. Branches

	/**
	 * Returns the Branch to which the grabber belongs. May be null.
	 * 
	 * @see #hasBranch(Branch)
	 * @see #resetBranch(Branch)
	 * @see #pruneBranch(Branch)
	 * @see #branches()
	 * @see #resetBranches()
	 * @see #pruneBranches()
	 */
	public Branch<?> branch(Grabber g) {
		for (Branch<?> b : brnchs)
			if (b.hasGrabber(g))
					return b;
		return null;
	}
	
	/**
	 * Returns the list of appended branches.
	 * 
	 * @see #hasBranch(Branch)
	 * @see #resetBranch(Branch)
	 * @see #pruneBranch(Branch)
	 * @see #branch(Grabber)
	 * @see #resetBranches()
	 * @see #pruneBranches()
	 */
	public List<Branch<?>> branches() {
		return brnchs;
	}

	/**
	 * Internal use. Branches should be appended through derived agents.
	 */
	protected boolean appendBranch(Branch<?> branch) {
		if (branch == null)
			return false;
		if (!brnchs.contains(branch)) {
			this.brnchs.add(branch);
			return true;
		}
		return false;
	}
	
	/*
	// produces a name clash with iAgents
	public <E extends Enum<E>> Branch<E> appendBranch(String name) {
		return new Branch<E>(this, name);
	}
	//*/
	
	/**
	 * Returns true if branch is appended to the agent and false otherwise.
	 * 
	 * @see #branches()
	 * @see #resetBranch(Branch)
	 * @see #pruneBranch(Branch)
	 * @see #branch(Grabber)
	 * @see #resetBranches()
	 * @see #pruneBranches()
	 */
	public boolean hasBranch(Branch<?> branch) {
		return brnchs.contains(branch);
	}

	/**
	 * Removes all interactive grabber objects from branch.
	 * 
	 * @see #hasBranch(Branch)
	 * @see #resetBranches()
	 * @see #pruneBranch(Branch)
	 * @see #branch(Grabber)
	 * @see #hasBranch(Branch)
	 * @see #pruneBranches()
	 */
	public void resetBranch(Branch<?> branch) {
		branch.reset();
		if(branch == this.defaultGrabberBranch)
			setDefaultGrabber(null);
		if(branch == this.trackedGrabberBranch)
			trackedGrabber = null;
	}
	
	/**
	 * Removes all interactive grabber objects from all branches appended to this agent.
	 * 
	 * @see #hasBranch(Branch)
	 * @see #resetBranch(Branch)
	 * @see #pruneBranch(Branch)
	 * @see #branch(Grabber)
	 * @see #hasBranch(Branch)
	 * @see #pruneBranches()
	 */
	public void resetBranches() {
		for (Branch<?> branch : brnchs)
			resetBranch(branch);
	}

	/**
	 * Calls {@link #resetBranch(Branch)} and then removes the branch from the agent.
	 * 
	 * @see #hasBranch(Branch)
	 * @see #resetBranch(Branch)
	 * @see #resetBranches()
	 * @see #branch(Grabber)
	 * @see #hasBranch(Branch)
	 * @see #pruneBranches()
	 */
	public boolean pruneBranch(Branch<?> branch) {
		if (brnchs.contains(branch)) {
			this.resetBranch(branch);
			this.brnchs.remove(branch);
			return true;
		}
		return false;
	}

	/**
	 * Calls {@link #resetBranch(Branch)} on all branches appended to this agent and the removes them.
	 * 
	 * @see #hasBranch(Branch)
	 * @see #resetBranch(Branch)
	 * @see #resetBranches()
	 * @see #branch(Grabber)
	 * @see #hasBranch(Branch)
	 * @see #pruneBranch(Branch)
	 */
	public void pruneBranches() {
		resetBranches();
		brnchs.clear();
	}

	/**
	 * Returns a String with a detailed description of this Agent.
	 */
	public String info() {
		String description = super.info();
		description += "Branches' info\n";
		int index = 1;
		for (Branch<?> branch : brnchs) {
			description += index;
			description += ". ";
			description += branch.info();
			index++;
		}
		return description;
	}

	@Override
	protected Grabber updateTrackedGrabber(BogusEvent event) {
		if(super.updateTrackedGrabber(event) == null) {
			trackedGrabberBranch = null;//this seems an important line :p 
			for (Branch<?> branch : brnchs) {
				InteractiveGrabber<?> iGrabber = branch.updateTrackedGrabber(event);
				if(iGrabber != null) {
					trackedGrabber = iGrabber;
					this.trackedGrabberBranch = branch;
					return trackedGrabber();
				}
			}
		}
		return trackedGrabber();
	}

	@Override
	protected boolean handle(BogusEvent event) {
		if (event == null || !handler.isAgentRegistered(this) || inputHandler() == null)
			return false;
		if (event instanceof MotionEvent)
			if (((MotionEvent) event).isAbsolute())
				if (event.isNull() && !event.flushed())
					return false;
		if (event instanceof MotionEvent)
			((MotionEvent) event).modulate(sensitivities((MotionEvent) event));
		Grabber inputGrabber = inputGrabber();
		if (inputGrabber != null) {
			if (inputGrabber instanceof InteractiveGrabber<?>) {
				Branch<?> t = trackedGrabber() != null ? trackedGrabberBranch : defaultGrabberBranch;
				return trackedGrabber() != null ? t.handleTrackedGrabber(event) : t.handleDefaultGrabber(event);
			}
			return inputHandler().enqueueEventTuple(new EventGrabberTuple(event, inputGrabber));
		}
		return false;
	}

	@Override
	public boolean setDefaultGrabber(Grabber grabber) {
		if( !super.setDefaultGrabber(grabber) )
			for (Branch<?> b : brnchs)
				if( b.setDefaultGrabber(grabber) ) {
					this.defaultGrabber = grabber;
					this.defaultGrabberBranch = b;
					return true;
				}
		return false;		
	}
}